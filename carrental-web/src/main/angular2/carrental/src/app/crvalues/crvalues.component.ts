/**
 *    Copyright 2016 Sven Loesekann

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
import { Component, OnInit, Input , OnDestroy} from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { CrPortfolio } from '../crTypes';
import { CrValuesValidators } from '../shared/crvalues.validators';
import { Subscription }   from 'rxjs/Subscription';

@Component({    
    selector: 'app-crvalues',
    templateUrl: './crvalues.component.html',
    styleUrls: ['./crvalues.component.css']         
})
export class CrValuesComponent implements OnInit, OnDestroy {
    form: FormGroup;
    fcNames = ['anzahlPkw', 'anzahlLkw', 'mieteAbgerechnetPkw', 'mieteAbgerechnetLkw'];
    @Input() crvalues: CrPortfolio;
    updateTotalsSub: Subscription[] = [];

    constructor(fb: FormBuilder) {
        this.form = fb.group({
            anzahlPkw: ['', CrValuesValidators.positiveIntValidator],
            mieteAbgerechnetPkw: ['', CrValuesValidators.positiveIntValidator],
            anzahlLkw: ['', CrValuesValidators.positiveIntValidator],
            mieteAbgerechnetLkw: ['', CrValuesValidators.positiveIntValidator]
        },{
            validator: this.validate.bind(this)
        });
    }

    ngOnInit() {
        let fc = <FormControl>this.form.controls[this.fcNames[0]];
        fc.setValue(this.crvalues.anzahlPkw);        
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {this.crvalues.anzahlPkw = value; this.updateTotals(value)}));      
        fc = <FormControl>this.form.controls[this.fcNames[1]];
        fc.setValue(this.crvalues.anzahlLkw);        
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {this.crvalues.anzahlLkw = value; this.updateTotals(value)}));
        fc = <FormControl>this.form.controls[this.fcNames[2]];
        fc.setValue(this.crvalues.mieteAbgerechnetPkw);
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {this.crvalues.mieteAbgerechnetPkw = value; this.updateTotals(value)}));        
        fc = <FormControl>this.form.controls[this.fcNames[3]];
        fc.setValue(this.crvalues.mieteAbgerechnetLkw);  
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {this.crvalues.mieteAbgerechnetLkw = value; this.updateTotals(value)}));    
//        this.validate();
        this.crvalues.mieteGeplantTotal = this.crvalues.mieteGeplantPkw + this.crvalues.mieteGeplantLkw;
        this.updateTotals(null);
    }

    ngOnDestroy() {
        for(let sub of this.updateTotalsSub) {
            sub.unsubscribe();
        }
    }
    
    updateTotals(value: any): void {        
        this.crvalues.anzahlTotal = parseInt(this.form.controls[this.fcNames[0]].value)  + parseInt(this.form.controls[this.fcNames[1]].value);
        this.crvalues.mieteAbgerechnetTotal = parseInt(this.form.controls[this.fcNames[2]].value) + parseInt(this.form.controls[this.fcNames[3]].value);
        //console.log("updateTotals("+value+") called.");
    }

    validate(group: FormGroup): boolean {
        let myFcNames = ['anzahlPkw', 'anzahlLkw', 'mieteAbgerechnetPkw', 'mieteAbgerechnetLkw'];
//        myFcNames.forEach(key => console.log(group.controls[key].value));
//        console.log('-----------------------');
        let invalidFcs =  myFcNames.filter(fcn => !CrValuesComponent.validNumber(group.controls[fcn].value));
        let valid = invalidFcs.length === 0;
        return valid;
    }

    static validNumber(value: AbstractControl): boolean {
        if(!value) return false;
        let myValue = parseInt(value.value, 20);
        let ret = isNaN(myValue);
        if (!ret && myValue >= 0) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }
}
