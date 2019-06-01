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

import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { AppRoutingModule }  from './app-routing.module';
import { CrlistComponent } from './crlist/crlist.component';
import { CrdetailComponent } from './crdetail/crdetail.component';
import { CrValuesComponent } from './crvalues/crvalues.component';
import { CrvaluesdComponent } from './crvaluesd/crvaluesd.component';
import { CrdateComponent } from './crdate/crdate.component';
import { CrrootComponent } from './crroot/crroot.component';
import { CruploadComponent } from './crupload/crupload.component';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {APP_BASE_HREF} from '@angular/common';
import {HttpTestingController} from "@angular/common/http/testing";
import { NumberSeparatorPipe } from './shared/number-separator.pipe';


describe('AppComponent', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        CrrootComponent,
        CrlistComponent,
        CrdetailComponent,
        CrValuesComponent,
        CrvaluesdComponent,
        CrdateComponent,
        NumberSeparatorPipe, 
        CruploadComponent
      ], 
      imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,        
        AppRoutingModule
      ],
      providers: [{provide: APP_BASE_HREF, useValue : '/' }]
    });
    TestBed.compileComponents();
  });

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it(`should have as title 'app works!'`, async(() => {
    const fixture =  TestBed.createComponent(AppComponent);    
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('carrental works!');
  }));

  it('should render title in a h1 tag', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('carrental works!');
  })); 
});
