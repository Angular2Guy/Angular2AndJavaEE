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
import {ActivatedRoute, Router } from '@angular/router';
import {ISubscription} from 'rxjs/Subscription';
import { Observable }        from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {CrRestService} from '../crrest.service';
import {CrTableRow} from '../crTypes';
import { environment } from '../../environments/environment';
import { PlatformLocation } from '@angular/common';

@Component({  
  selector: 'app-crlist',
  templateUrl: './crlist.component.html',
  styleUrls: ['./crlist.component.css'],    
})
export class CrlistComponent implements OnInit, OnDestroy {
  tableRows: Observable<CrTableRow[]>;
  errorMsg: string;
  private routeSub: ISubscription;
  //years: string[] = [];
  //private tableRowsSub: ISubscription;
    
  constructor(private route: ActivatedRoute,private router: Router, private service: CrRestService, private pl: PlatformLocation ) {}

  ngOnInit(): void {
      this.routeSub = this.route.params.subscribe(params => {
        let mnr = params['mnr'];
        this.tableRows = this.service.getCrTableRows(mnr).catch(error => {this.errorMsg = error; return Observable.of<CrTableRow[]>([]);});        
        //this.tableRowsSub = this.tableRows.subscribe(rows => {rows.forEach(row => {this.years.push(row.jahr);console.log(row.jahr);}); return rows});
      });
  }
  
  ngOnDestroy() {
    this.routeSub.unsubscribe();
    //this.tableRowsSub.unsubscribe();
  }
  
  showPdf(num: string) {
      let url = environment.production ? this.pl.getBaseHrefFromDOM() +this.service._crPdfUrlProd : this.service._crPdfUrlDev;
      url = this.service.cleanUrl(url);
      url = url.replace( "{mietNr}", num );
      console.log(url);
      window.open(url);
//      this.service.getCrPdf(num).subscribe((res) => {
//          let fileUrl = URL.createObjectURL(res);
//          console.log(fileUrl);
//          window.open(fileUrl);
//      });
  }
}
