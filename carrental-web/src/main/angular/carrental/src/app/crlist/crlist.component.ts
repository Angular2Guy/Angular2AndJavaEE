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
import {ActivatedRoute, Router, ParamMap } from '@angular/router';
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
  styleUrls: ['./crlist.component.scss'],    
})
export class CrlistComponent implements OnInit {
  tableRows: Observable<CrTableRow[]>;
  errorMsg: string;  
  modalvisible = false;
    
  constructor(private route: ActivatedRoute,private router: Router, private service: CrRestService, private pl: PlatformLocation ) {}

  ngOnInit(): void {
      let observ = this.route.paramMap.switchMap((params: ParamMap)=>         
        this.service.getCrTableRows(params.get('mnr')));
      this.tableRows = observ.catch(error => {this.errorMsg = error; return Observable.of<CrTableRow[]>([]);});
  }
  
  showPdf(num: string) {
      let url = this.service._baseHRef + this.service._crPdfUrlProd;
      url = this.service.cleanUrl(url);
      url = url.replace( "{mietNr}", num );
      console.log(url);
      window.open(url);
  }
  
  showModal() {
      this.modalvisible = !this.modalvisible;
  }
}
