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
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CrlistComponent } from "./crlist/crlist.component";
import { CrdetailComponent } from "./crdetail/crdetail.component";
import { AppComponent } from "./app.component";
import { CrrootComponent } from "./crroot/crroot.component";

const routes: Routes = [
  { path: "crdetail/mietenr/:mnr/jahr/:jahr", component: CrdetailComponent },
  { path: "crlist/mietenr/:mnr", component: CrlistComponent },
  { path: "crroot", component: CrrootComponent },
  { path: "**", redirectTo: "/crroot", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {})],
  exports: [RouterModule],
  providers: [],
})
export class AppRoutingModule {}
