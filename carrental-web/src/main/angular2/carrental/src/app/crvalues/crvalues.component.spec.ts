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
/* tslint:disable:no-unused-variable */

import { By }           from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { TestBed, async } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { CrValuesComponent } from './crvalues.component';
import { CrPortfolio } from '../crTypes';

describe('Component: CrValues', () => {
  it('validate should return false', () => {
    let component = new CrValuesComponent(new FormBuilder());
    let param = new CrPortfolioImpl(null, null, null, null);
    component.crvalues = param;
    component.ngOnInit();
    expect(!component.form.valid).toBeTruthy();
  });
  
  it('validate should return true', () => {
    let component = new CrValuesComponent(new FormBuilder());
    let param = new CrPortfolioImpl(1, 1, 1, 1);
    component.crvalues = param;
    component.ngOnInit();
    expect(component.form.valid).toBeTruthy();
  });
    
  it('validate should return false', () => {
    let component = new CrValuesComponent(new FormBuilder());
    let param = new CrPortfolioImpl(-1, -1, -1, -1);
    component.crvalues = param;
    component.ngOnInit();
    expect(!component.form.valid).toBeTruthy();
  });
  
//  it('make tests fail', () => {
//      expect(false).toBeTruthy();
//  })
}); 

class CrPortfolioImpl implements CrPortfolio {
    
    constructor(anzahlPkw: number, anzahlLkw: number, mieteAbgerechnetPkw: number, mieteAbgerechnetLkw: number) {
        this.anzahlPkw = anzahlPkw;
        this.anzahlLkw = anzahlLkw;
        this.mieteAbgerechnetPkw = mieteAbgerechnetPkw;
        this.mieteAbgerechnetLkw = mieteAbgerechnetLkw;
    }
    
    id: string;  
    bezeichnung: string; 
    anzahlPkw: number;
    anzahlLkw: number;
    anzahlTotal: number;
    mieteGeplantPkw: number;
    mieteGeplantLkw: number;
    mieteGeplantTotal: number;
    mieteAbgerechnetPkw: number;
    mieteAbgerechnetLkw: number;
    mieteAbgerechnetTotal: number;
}