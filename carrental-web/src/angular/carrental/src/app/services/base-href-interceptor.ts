import { Injectable, Inject } from '@angular/core';
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
	req = req.clone({
		headers: this.defaultReqHeader,
		url: (!this.platformLocation.getBaseHrefFromDOM() || this.platformLocation.getBaseHrefFromDOM().length < 2 ? '' : this.platformLocation.getBaseHrefFromDOM()) + req.url, 
	});	
	console.log(this.platformLocation.getBaseHrefFromDOM());
  	return next.handle(req).pipe(tap(event => event, event => this.handleError(event)));
  }

  private handleError(event: HttpEvent<any>): HttpEvent<any> {
	if(event instanceof HttpErrorResponse) {
		const error = <HttpErrorResponse> event;
		console.log(`failed: ${error.message}`);
		this.crRestService.putCrLogMsg(new CrLogMsgImpl('Error', error.message)).subscribe();
	}
	return event;
  }	
}