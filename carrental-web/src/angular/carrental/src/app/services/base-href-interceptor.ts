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
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, of} from 'rxjs';
import { tap } from 'rxjs/operators';
import { PlatformLocation } from '@angular/common'; 
import { CrRestService } from './crrest.service';
import { CrLogMsgImpl } from '../dtos/crClasses';

@Injectable()
export class BaseHrefInterceptor implements HttpInterceptor {
	private defaultReqHeader = new HttpHeaders().set( 'Content-Type', 'application/json' );
	
	constructor(
		private platformLocation: PlatformLocation,
	 	private crRestService: CrRestService) {}
	
 intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
	const myBaseHref = !this.platformLocation.getBaseHrefFromDOM() || this.platformLocation.getBaseHrefFromDOM().length < 2 ? '//'.split('/') : this.platformLocation.getBaseHrefFromDOM().split('/');
	req = req.clone({
		headers: this.defaultReqHeader,
		url: (myBaseHref[1].length > 0 ? '/'+myBaseHref[1] : '') + req.url, 
	});	
	//console.log(myBaseHref);
  	return next.handle(req).pipe(tap(event => event, event => this.handleError(event)));
  }

  private handleError(event: HttpEvent<any>): HttpEvent<any> {
	if(event instanceof HttpErrorResponse) {
		const error = <HttpErrorResponse> event;
		console.log(`failed: ${error.message}`);
		if(error.url.indexOf('crLog') < 0) {
			this.crRestService.putCrLogMsg(new CrLogMsgImpl('Error', error.message)).subscribe();
		}
	}
	return event;
  }	
}