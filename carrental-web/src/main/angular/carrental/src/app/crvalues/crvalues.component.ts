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
import { Component, OnInit, Input , OnDestroy, EventEmitter, Output} from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { CrPortfolio } from '../crTypes';
import { CrValuesValidators } from '../shared/crvalues.validators';
import { Subscription }   from 'rxjs';
import { Utils } from '../shared/utils';

@Component({    
    selector: 'app-crvalues',
    templateUrl: './crvalues.component.html',
    styleUrls: ['./crvalues.component.scss']         
})
export class CrValuesComponent implements OnInit, OnDestroy {
    form: FormGroup;
    fcNames = ['anzahlPkw', 'anzahlLkw', 'mieteAbgerechnetPkw', 'mieteAbgerechnetLkw'];
    @Input() crvalues: CrPortfolio;
    updateTotalsSub: any[] = [];  
    @Output() valuesValid = new EventEmitter<boolean>();

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
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {
                let myValue = Utils.removeSeparators(value); 
                this.crvalues.mieteAbgerechnetPkw = value; 
                this.updateTotals(value);
                
                }));        
        fc = <FormControl>this.form.controls[this.fcNames[3]];
        fc.setValue(this.crvalues.mieteAbgerechnetLkw);  
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {
                let myValue = Utils.removeSeparators(value); 
                this.crvalues.mieteAbgerechnetLkw = value; 
                this.updateTotals(value)}));    
        this.crvalues.mieteGeplantTotal = this.crvalues.mieteGeplantPkw + this.crvalues.mieteGeplantLkw;
        this.updateTotals(null);
    }

    ngOnDestroy() {
        for(let sub of this.updateTotalsSub) {
            sub.unsubscribe();
        }
    }
    
    updateTotals(value: any): void {         
        this.crvalues.anzahlTotal = (isNaN(parseInt(this.form.controls[this.fcNames[0]].value)) ? 0 : parseInt(this.form.controls[this.fcNames[0]].value))  
            + (isNaN(parseInt(this.form.controls[this.fcNames[1]].value)) ? 0 : parseInt(this.form.controls[this.fcNames[1]].value));
        this.crvalues.mieteAbgerechnetTotal = (isNaN(parseInt(Utils.removeSeparators(String(this.form.controls[this.fcNames[2]].value)).toString(10))) ? 0 : parseInt(Utils.removeSeparators(String(this.form.controls[this.fcNames[2]].value)).toString(10))) 
            + (isNaN(parseInt(Utils.removeSeparators(String(this.form.controls[this.fcNames[3]].value)).toString(10))) ? 0 : parseInt(Utils.removeSeparators(String(this.form.controls[this.fcNames[3]].value)).toString(10)));
        //console.log("updateTotals("+value+") called.");
    }

    validate(group: FormGroup): boolean {
        let myFcNames = ['anzahlPkw', 'anzahlLkw', 'mieteAbgerechnetPkw', 'mieteAbgerechnetLkw'];
//        myFcNames.forEach(key => console.log(group.controls[key].value));
//        console.log('-----------------------');
        let invalidFcs =  myFcNames.filter(fcn => ! this.validNumber(group.controls[fcn]));
        let valid = invalidFcs.length === 0;
        this.valuesValid.emit(valid);
        return valid;
    }
    
    private validNumber(value: AbstractControl): boolean {
        if(!value || !value.value) return false;
        const valueStr = value.value.toString(10).split('').filter(c => c != "'").join('');
        let ret = isNaN(valueStr);
        const myValue = parseInt(valueStr, 20);
        if (!ret && myValue >= 0) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }
}
