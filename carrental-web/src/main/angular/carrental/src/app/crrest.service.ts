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
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PlatformLocation } from '@angular/common';
import { CrTableRow, CrDetail, CrPeriod, CrPortfolio, CrLogMsg } from './crTypes';
import { CrTableRowImpl, CrDetailImpl, CrPeriodImpl, CrPortfolioImpl, CrLogMsgImpl } from './crClasses';
import { Observable, throwError } from 'rxjs';
import { map, debounceTime, catchError, tap} from 'rxjs/operators';
import { environment } from '../environments/environment';

@Injectable()
export class CrRestService {
    static readonly NEWID = 'newId';
    private _crTableUrlProd = '/rest/model/crTable/mietNr/{mietNr}';  // URL to web api
    private _crDetailUrlProd = '/rest/model/crDetail/mietNr/{mietNr}/jahr/{jahr}';
    public _crPdfUrlProd = '/rest/model/crTable/mietNr/{mietNr}/pdf';
    public _baseHRef = '/carrental-web';
    private _crLogUrlProd = '/rest/model/crLog';
    private _reqOptionsArgs =  { headers: new HttpHeaders().set( 'Content-Type', 'application/json' ) };

    constructor( private http: HttpClient, private pl: PlatformLocation ) {        
    }

    getCrTableRows( policeNr: string ) : Observable<CrTableRow[]> {
        let url = this._baseHRef + this._crTableUrlProd;       
        url = this.cleanUrl(url);
        url = url.replace( "{mietNr}", policeNr );
        console.log(url);
        return this.http.get<CrTableRow[]>( url, this._reqOptionsArgs);
    }

    getCrDetail( policeNr: string, jahr: string ) : Observable<CrDetail>{
        if(policeNr === CrRestService.NEWID || jahr === CrRestService.NEWID) {
            return Observable.create(observer => {
                observer.next(this.createEmptyTree());
                observer.complete();
            });
        }
        let url = this._baseHRef + this._crDetailUrlProd;
        url = this.cleanUrl(url);
        url = url.replace( "{mietNr}", policeNr ).replace( "{jahr}", jahr );
        console.log(url);
        return this.http.get<CrDetail>( url, this._reqOptionsArgs);
    }
 
    private createEmptyTree() : CrDetail {
        let crPortfolio = new CrPortfolioImpl(null,null,null,null,null,null,null,null,null,null,null);
        let crPeriod = new CrPeriodImpl(null,null,null,[crPortfolio]);        
        let crDetail = new CrDetailImpl(null,true,null,null,null,[crPeriod],[]);
        return crDetail;
    }
    
    postCrDetail(policeNr: string, jahr: string, crDetail: CrDetail) : Observable<CrDetail> {
        let url = this._baseHRef + this._crDetailUrlProd;
        url = this.cleanUrl(url);
        url = url.replace( "{mietNr}", policeNr ).replace( "{jahr}", jahr );
        let json = this.cleanString(JSON.stringify(crDetail));
        return this.http.post<CrDetail>(url, json, this._reqOptionsArgs);
    }
    
    putCrDetail(policeNr: string, jahr: string, crDetail: CrDetail) : Observable<CrDetail> {
        let url = this._baseHRef + this._crDetailUrlProd;
        url = this.cleanUrl(url);
        url = url.replace( "{mietNr}", policeNr ).replace( "{jahr}", jahr );
        let json = this.cleanString(JSON.stringify(crDetail));        
        return this.http.put<CrDetail>(url, json, this._reqOptionsArgs);
    }
    
    deleteCrDetail(policeNr: string, jahr: string, crDetail: CrDetail) : Observable<CrDetail> {
        let url = this._baseHRef + this._crDetailUrlProd;
        url = this.cleanUrl(url);
        url = url.replace( "{mietNr}", policeNr ).replace( "{jahr}", jahr );        
        return this.http.delete<CrDetail>(url, this._reqOptionsArgs);
    }
   
    putCrLogMsg(crLogMsg: CrLogMsg): Observable<CrLogMsg> {
        let url = this._baseHRef + this._crLogUrlProd;
        url = this.cleanUrl(url);
        return this.http.put<CrLogMsg>(url, crLogMsg, this._reqOptionsArgs);
    }
    
    private cleanString(str: string): string {
        while(str.indexOf("'") > 0) {
            str = str.replace("'","");
        }
        return str;
    } 
    
    cleanUrl(url: string) : string {
        console.log( url );
        if ( environment.production ) {
            url = url.replace('//','/'); 
            url = url.replace('/en','');
            url = url.replace('/de','');
            console.log( url );            
        }  
        return url;
    }
   
    
}
