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
import { CrPortfolio } from '../dtos/crTypes';
import { CrValuesValidators } from '../shared/crvalues.validators';
import { Utils } from '../shared/utils';

enum FormFields {
    AnzahlPkw = 'anzahlPkw',
    AnzahlLkw = 'anzahlLkw',
    MieteAbgerechnetPkw = 'mieteAbgerechnetPkw',
    MieteAbgerechnetLkw = 'mieteAbgerechnetLkw'
}

@Component({
    selector: 'app-crvalues',
    templateUrl: './crvalues.component.html',
    styleUrls: ['./crvalues.component.scss']
})
export class CrValuesComponent implements OnInit, OnDestroy {
    @Input() crvalues: CrPortfolio;
    protected updateTotalsSub: any[] = [];
    @Output() valuesValid = new EventEmitter<boolean>();
    protected FormFields = FormFields;
    form: FormGroup;

    constructor(fb: FormBuilder) {
        this.form = fb.group({
            [FormFields.AnzahlPkw]: ['', CrValuesValidators.positiveIntValidator],
            [FormFields.MieteAbgerechnetPkw]: ['', CrValuesValidators.positiveIntValidator],
            [FormFields.AnzahlLkw]: ['', CrValuesValidators.positiveIntValidator],
            [FormFields.MieteAbgerechnetLkw]: ['', CrValuesValidators.positiveIntValidator]
        });
    }

    ngOnInit() {
        let fc = <FormControl>this.form.controls[FormFields.AnzahlPkw];
        fc.setValue(this.crvalues.anzahlPkw);
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {this.crvalues.anzahlPkw = value; this.updateTotals(value);}));
        fc = <FormControl>this.form.controls[FormFields.AnzahlLkw];
        fc.setValue(this.crvalues.anzahlLkw);
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {this.crvalues.anzahlLkw = value; this.updateTotals(value);}));
        fc = <FormControl>this.form.controls[FormFields.MieteAbgerechnetPkw];
        fc.setValue(this.crvalues.mieteAbgerechnetPkw);
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {
                const myValue = Utils.removeSeparators(value);
                this.crvalues.mieteAbgerechnetPkw = value;
                this.updateTotals(value);

                }));
        fc = <FormControl>this.form.controls[FormFields.MieteAbgerechnetLkw];
        fc.setValue(this.crvalues.mieteAbgerechnetLkw);
        this.updateTotalsSub.push(fc.valueChanges.subscribe(value => {
                const myValue = Utils.removeSeparators(value);
                this.crvalues.mieteAbgerechnetLkw = value;
                this.updateTotals(value);}));
        this.crvalues.mieteGeplantTotal = this.crvalues.mieteGeplantPkw + this.crvalues.mieteGeplantLkw;
        this.updateTotals(null);
    }

    ngOnDestroy() {
        for(const sub of this.updateTotalsSub) {
            sub.unsubscribe();
        }
    }

    updateTotals(value: any): void {
        this.crvalues.anzahlTotal = (isNaN(parseInt(this.form.controls[FormFields.AnzahlPkw].value)) ? 0 : parseInt(this.form.controls[FormFields.AnzahlPkw].value))
            + (isNaN(parseInt(this.form.controls[FormFields.AnzahlLkw].value)) ? 0 : parseInt(this.form.controls[FormFields.AnzahlLkw].value));
        this.crvalues.mieteAbgerechnetTotal = (isNaN(parseInt(Utils.removeSeparators(String(this.form.controls[FormFields.MieteAbgerechnetPkw].value)).toString(10))) ? 0 : parseInt(Utils.removeSeparators(String(this.form.controls[FormFields.MieteAbgerechnetPkw].value)).toString(10)))
            + (isNaN(parseInt(Utils.removeSeparators(String(this.form.controls[FormFields.MieteAbgerechnetLkw].value)).toString(10))) ? 0 : parseInt(Utils.removeSeparators(String(this.form.controls[FormFields.MieteAbgerechnetLkw].value)).toString(10)));
        //console.log("updateTotals("+value+") called.");
        this.valuesValid.emit(this.form.valid);
    }

}
