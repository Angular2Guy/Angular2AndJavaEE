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
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { CrRestService } from '../crrest.service';
import { CrDetail, CrPeriod } from '../crTypes';
import { CrValuesComponent } from '../crvalues/crvalues.component';

@Component( {
    selector: 'app-crdetail',
    templateUrl: './crdetail.component.html',
    styleUrls: ['./crdetail.component.scss'],
} )
export class CrdetailComponent implements OnInit {
    
    crDetail: CrDetail;
    crPeriods: CrPeriod[];
    crEditmode: boolean;
    private mnr: string;
    private jahr: string;
    private valid: boolean;

    constructor( private route: ActivatedRoute, private router: Router, private service: CrRestService ) {
        this.crEditmode = false;
    }

    ngOnInit(): void {
        this.init();
    }

    init():void {
        this.mnr = this.route.snapshot.paramMap.get( 'mnr' );
        this.jahr = this.route.snapshot.paramMap.get( 'jahr' );
        this.service.getCrDetail( this.mnr, this.jahr )
            .subscribe( lsdD => { this.crDetail = <CrDetail>lsdD; this.crPeriods = ( <CrDetail>lsdD ).crPeriods; });
    }
    
    valuesValid( valid: boolean ) {
        this.valid = valid;
    }

    toggleEditmode(): void {
        this.crEditmode = !this.crEditmode;
    }

    update(): void {
        if ( this.valid ) {
            if ( this.crDetail.id) {
                this.service.putCrDetail( this.mnr, this.jahr, this.crDetail ).subscribe( lsdD => { 
                    let urltree = this.router.createUrlTree( ['crlist/mietenr/', this.mnr] );
                    this.router.navigateByUrl( urltree );
                    });
            } else {
                this.service.postCrDetail( this.mnr, this.jahr, this.crDetail ).subscribe( lsdD => {
                    let urltree = 'crlist/mietenr/' + 1;
                    this.router.navigateByUrl( urltree );
                });
            }
        }
    }

    create(): void {
        let urlStr = 'crdetail/mietenr/' + CrRestService.NEWID + '/jahr/' + CrRestService.NEWID;
        this.router.navigateByUrl( urlStr ).then(result => this.init());        
    }

    delete(): void {
        if ( this.valid ) {
            this.service.deleteCrDetail( this.mnr, this.jahr, this.crDetail ).subscribe( lsdD => {
                let urltree = 'crlist/mietenr/' + 1;
                this.router.navigateByUrl( urltree );
            });
        }
    }
}
