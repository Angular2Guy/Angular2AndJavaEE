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
import { Component, OnInit, OnDestroy } from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from '@angular/router';
import {ISubscription} from 'rxjs/Subscription';
import {CrRestService} from '../crrest.service';
import {CrDetail, CrPeriod} from '../crTypes';
import {CrValuesComponent} from '../crvalues/crvalues.component';
import 'rxjs/add/operator/switchMap';

@Component({  
  selector: 'app-crdetail',
  templateUrl: './crdetail.component.html',
  styleUrls: ['./crdetail.component.scss'],
})
export class CrdetailComponent  implements OnInit {
  errorMsg: string;
  crDetail: CrDetail;
  crPeriods: CrPeriod[];
  crEditmode: boolean;
  private mnr: string;
  private jahr: string;
    
  constructor(private route: ActivatedRoute, private router: Router, private service: CrRestService) {
      this.crEditmode = false;
  }

  ngOnInit(): void {  
      let observ = this.route.paramMap.switchMap((params: ParamMap)=> {
          this.mnr = params.get('mnr');
          this.jahr = params.get('jahr');
          return this.service.getCrDetail(this.mnr, this.jahr);
      });
      observ.subscribe(lsdD => {this.crDetail = <CrDetail> lsdD; this.crPeriods = (<CrDetail> lsdD).crPeriods;}, error => this.errorMsg = error);
  }

  toggleEditmode(): void {
      this.crEditmode = !this.crEditmode;
  }
  
  update(): void { 
      if(this.crDetail.id) {
          this.service.putCrDetail(this.mnr, this.jahr, this.crDetail).subscribe(lsdD => {this.router.createUrlTree(['crdetail/mietenr/',(<CrDetail> lsdD).mieteNr, '/jahr/',(<CrDetail> lsdD).jahr]);}, error => this.errorMsg = error);
      } else {
          this.service.postCrDetail(this.mnr, this.jahr, this.crDetail).subscribe(lsdD => {
              let urltree = 'crlist/mietenr/'+1;
              this.router.navigateByUrl(urltree);
          }, error => this.errorMsg = error);          
      }
  }
  
  create(): void {      
      let urlStr = 'crdetail/mietenr/'+CrRestService.NEWID+ '/jahr/'+CrRestService.NEWID;
      this.router.navigateByUrl(urlStr);
  }
  
  delete(): void {
      this.service.deleteCrDetail(this.mnr, this.jahr, this.crDetail).subscribe(lsdD => {
          let urltree = 'crlist/mietenr/'+1;
          this.router.navigateByUrl(urltree);
      }, error => this.errorMsg = error);
  }
}
