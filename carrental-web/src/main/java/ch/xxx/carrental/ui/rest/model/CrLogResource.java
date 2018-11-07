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
package ch.xxx.carrental.ui.rest.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ch.xxx.carrental.ui.dto.CrLogMsg;
import ch.xxx.carrental.ui.interceptor.DisableCaching;
import ch.xxx.carrental.ui.service.CrLoggingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestScoped
@Path("/model/crLog")
@Consumes({ "application/json" })
@Api(value = "/model/crLog")
public class CrLogResource {
	@Inject
	private CrLoggingService service;
	
	public CrLogResource() {		
	}
	
	@PUT
	@DisableCaching
	@ApiOperation(value = "Log Service")
	public Response updateDetails(CrLogMsg crLogMsg) {
		return Response.ok(service.logMsg(crLogMsg)).build();
	}
}
