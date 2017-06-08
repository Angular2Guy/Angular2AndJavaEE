package ch.xxx.carrental.ui.rest.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;

import ch.xxx.carrental.ui.interceptor.DisableCaching;
import ch.xxx.carrental.ui.service.CrTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/model/crTable")
@Produces({ "application/json" })
@Api(value="/model/crTable")
public class CrTableResource {
	@Inject
	private CrTableService service;

	@GET
	@Path("/mietNr/{mietNr}")
	@DisableCaching
	@ApiOperation(value="gets the rows for the table")
	public Response getAll(@PathParam("mietNr") final String mietNr, @HeaderParam("Origin") final String origin,
			@HeaderParam("Accept-Language") final String acceptLang) {
		String[] langs = acceptLang.split(",");
		Locale locale = Locale.forLanguageTag(langs[0]);
		if (origin != null && origin.contains("http://localhost")) {
			return Response.ok(service.readCrRowsByMiete(mietNr, locale)).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers",
							"X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").allow("OPTIONS").build();
		} else {
			return Response.ok(service.readCrRowsByMiete(mietNr, locale)).build();
		}

	}

	@OPTIONS
	@DisableCaching
	public Response getOptions(@HeaderParam("Origin") final String origin) {
		if (origin != null && origin.contains("http://localhost")) {
			return Response.ok().header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
					.header("Access-Control-Allow-Headers",
							"X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept")
					.allow("OPTIONS").build();
		} else {
			return Response.ok().build();
		}
	}

	@GET
	@Path("/mietNr/{mietNr}/pdf")
	@Produces("application/pdf")
	@DisableCaching
	@ApiOperation(value="provides the pdf files for display")
	public Response getPdf(@PathParam("mietNr") final String mietNr, @HeaderParam("Origin") final String origin,
			@HeaderParam("Accept-Language") final String acceptLang) {
		byte[] array = null;
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("../../pdf/Testdocument" + mietNr + ".pdf");
			if (inputStream == null) {
				throw new IOException();
			}
			array = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			return Response.status(Status.NOT_FOUND).entity("Failed to read File.").build();
		}
		if (origin != null && origin.contains("http://localhost")) {
			return Response.ok(array).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers",
							"X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
					.allow("OPTIONS").build();
		} else {
			return Response.ok(array).build();
		}
	}

}
