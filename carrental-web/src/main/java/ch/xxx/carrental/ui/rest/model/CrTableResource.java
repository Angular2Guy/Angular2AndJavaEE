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

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.exception.LocalEntityNotFoundException;
import ch.xxx.carrental.ui.exception.LocalValidationException;
import ch.xxx.carrental.ui.interceptor.DisableCaching;
import ch.xxx.carrental.ui.service.CrTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestScoped
@Path("/model/crTable")
@Produces({ "application/json" })
@Api(value = "/model/crTable")
public class CrTableResource {
	@Inject
	private CrTableService service;

	public CrTableResource() {
	}

	public CrTableResource(CrTableService service) {
		this.service = service;
	}

	@GET
	@Path("/mietNr/{mietNr}")
	@DisableCaching
	@ApiOperation(value = "gets the rows for the table", response = CrTableRow.class, responseContainer = "List")
	public Response getAll(@PathParam("mietNr") final String mietNr,
			@HeaderParam("Accept-Language") final String acceptLang)
			throws LocalEntityNotFoundException, LocalValidationException {
		String[] langs = acceptLang.split(",");
		Locale locale = Locale.forLanguageTag(langs[0]);
		return Response.ok(service.readCrRowsByMiete(mietNr, locale)).build();
	}

	@GET
	@Path("/mietNr/{mietNr}/pdf")
	@Produces("application/pdf")
	@DisableCaching
	@ApiOperation(value = "provides the pdf files for display")
	public Response getPdf(@PathParam("mietNr") final String mietNr,
			@HeaderParam("Accept-Language") final String acceptLang)
			throws LocalEntityNotFoundException, LocalValidationException {
		byte[] array = null;
		InputStream inputStream = this.service.readCrPdf(mietNr);
		try {
			array = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			throw new LocalEntityNotFoundException("Document not found.");
		}
		return Response.ok(array).build();
	}

}
