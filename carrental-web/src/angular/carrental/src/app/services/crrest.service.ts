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
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {
  CrTableRow,
  CrDetail,
  CrPeriod,
  CrPortfolio,
  CrLogMsg,
} from "../dtos/crTypes";
import {
  CrTableRowImpl,
  CrDetailImpl,
  CrPeriodImpl,
  CrPortfolioImpl,
  CrLogMsgImpl,
} from "../dtos/crClasses";
import { Observable, throwError } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: "root",
})
export class CrRestService {
  static readonly NEWID = "newId";

  constructor(private http: HttpClient) {}

  getCrTableRows(mietNr: string): Observable<CrTableRow[]> {
    return this.http.get<CrTableRow[]>(`/rest/model/crTable/mietNr/${mietNr}`);
  }

  getCrDetail(mietNr: string, jahr: string): Observable<CrDetail> {
    if (mietNr === CrRestService.NEWID || jahr === CrRestService.NEWID) {
      return Observable.create((observer) => {
        observer.next(this.createEmptyTree());
        observer.complete();
      });
    }
    return this.http.get<CrDetail>(
      `/rest/model/crDetail/mietNr/${mietNr}/jahr/${jahr}`
    );
  }

  private createEmptyTree(): CrDetail {
    const crPortfolio = new CrPortfolioImpl(
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
    const crPeriod = new CrPeriodImpl(null, null, null, [crPortfolio]);
    const crDetail = new CrDetailImpl(
      null,
      true,
      null,
      null,
      null,
      [crPeriod],
      []
    );
    return crDetail;
  }

  postCrDetail(
    mietNr: string,
    jahr: string,
    crDetail: CrDetail
  ): Observable<CrDetail> {
    return this.http.post<CrDetail>(
      `/rest/model/crDetail/mietNr/${mietNr}/jahr/${jahr}`,
      crDetail
    );
  }

  putCrDetail(
    mietNr: string,
    jahr: string,
    crDetail: CrDetail
  ): Observable<CrDetail> {
    return this.http.put<CrDetail>(
      `/rest/model/crDetail/mietNr/${mietNr}/jahr/${jahr}`,
      crDetail
    );
  }

  deleteCrDetail(
    mietNr: string,
    jahr: string,
    crDetail: CrDetail
  ): Observable<CrDetail> {
    return this.http.delete<CrDetail>(
      `/rest/model/crDetail/mietNr/${mietNr}/jahr/${jahr}`
    );
  }

  putCrLogMsg(crLogMsg: CrLogMsg): Observable<CrLogMsg> {
    return this.http.put<CrLogMsg>("/rest/model/crLog", crLogMsg);
  }
}
