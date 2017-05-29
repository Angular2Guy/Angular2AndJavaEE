package ch.xxx.carrental.ui.rest.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import ch.xxx.carrental.ui.interceptor.DisableCaching;
import ch.xxx.carrental.ui.service.CrTableService;

@Path("/model/crTable/mietNr/{mietNr}")
@Produces({ "application/json" })
public class CrTableResource {
	@Inject
	private CrTableService service;

	@GET
	@DisableCaching
	public Response getAll(@PathParam("mietNr") final String mietNr, @HeaderParam("Origin") final String origin, @HeaderParam("Accept-Language") final String acceptLang) {		
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
	@Path("/pdf")
	@Produces("application/pdf")
	@DisableCaching
	public Response getPdf(@PathParam("mietNr") final String mietNr, @HeaderParam("Origin") final String origin, @HeaderParam("Accept-Language") final String acceptLang) {
		byte[] array = null;
		try {			
			array = IOUtils.toByteArray(this.getClass().getResourceAsStream("../../pdf/BigCalendar.pdf"));			
		} catch (IOException e) {
			return Response.serverError().entity("Failed to read File.").build();
		}
		return Response.ok(array).header("Content-Disposition",
				"attachment; filename=BigCalendar.pdf").build();
	}
	
}
