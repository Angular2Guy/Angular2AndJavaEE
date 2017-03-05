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
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { TranslateModule} from 'ng2-translate/ng2-translate';

import { AppComponent } from './app.component';
import { CrlistComponent } from './crlist/crlist.component';
import { CrdetailComponent } from './crdetail/crdetail.component';
import { CrValuesComponent } from './crvalues/crvalues.component';
import { CrRestService } from './crrest.service';
import { AppRoutingModule }  from './app-routing.module';
import { CrvaluesdComponent } from './crvaluesd/crvaluesd.component';

@NgModule({
  declarations: [
    AppComponent,
    CrlistComponent,
    CrdetailComponent,
    CrValuesComponent,
    CrvaluesdComponent,    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    TranslateModule.forRoot(),
    AppRoutingModule
  ],
  providers: [CrRestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
