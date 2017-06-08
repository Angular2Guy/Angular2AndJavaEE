package ch.xxx.carrental.ui.rest.model;

import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.interceptor.DisableCaching;
import ch.xxx.carrental.ui.service.CrDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/model/crDetail")
@Produces({"application/json"})
@Consumes({"application/json"})
@Api(value="/model/crDetail")
public class CrDetailResource {
	@Inject
	private CrDetailService service;	
	
	@GET
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value="get the details for the mietNr and jahr")
	public Response getAll(@PathParam("mietNr") final String mietNr, @PathParam("jahr") final String jahr, @HeaderParam("Origin") final String origin,@HeaderParam("Accept-Language") final String acceptLang) {
		String[] langs = acceptLang.split(",");
		Locale locale = Locale.forLanguageTag(langs[0]);
		if (origin != null && origin.contains("http://localhost")) {
			return createLocalResponse(service.readCrDetail(mietNr, jahr, locale));					
		} else {
			return Response.ok(service.readCrDetail(mietNr, jahr, locale)).build();
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
	
	@PUT
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value="update the details for the mietNr and jahr")
	public Response updateDetails(CrDetail crDetail, @HeaderParam("Origin") final String origin) {
		if (origin != null && origin.contains("http://localhost")) {
			return createLocalResponse(service.updateCrDetail(crDetail));
		} else {
			return Response.ok(service.updateCrDetail(crDetail)).build();
		}
	}

	private Response createLocalResponse(Object serviceCall) {
		return Response.ok(serviceCall).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept")
				.allow("OPTIONS").build();
	}
	
	@POST
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value="create the details for the mietNr and jahr")
	public Response createDetails(CrDetail crDetail,@PathParam("mietNr") final String mietNr, @PathParam("jahr") final String jahr, @HeaderParam("Origin") final String origin) {
		if (origin != null && origin.contains("http://localhost")) {
			return createLocalResponse(service.createCrDetail(crDetail));
		} else {
			return Response.ok(service.createCrDetail(crDetail)).build();
		}
	}
	
	@DELETE
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value="delete the details for the mietNr and jahr")
	public Response deleteDetails(@PathParam("mietNr") final String mietNr, @PathParam("jahr") final String jahr, @HeaderParam("Origin") final String origin) {
		if (origin != null && origin.contains("http://localhost")) {
			return createLocalResponse(service.deleteCrDetail(mietNr, jahr));
		} else {
			return Response.ok(service.deleteCrDetail(mietNr, jahr)).build();
		}
	}
}
