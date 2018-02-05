package ch.xxx.carrental.ui.rest.model;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;
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

@RequestScoped
@Path("/model/crDetail")
@Produces({ "application/json" })
@Consumes({ "application/json" })
@Api(value = "/model/crDetail")
public class CrDetailResource {
	@Inject
	private CrDetailService service;

	@GET
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value = "get the details for the mietNr and jahr", response = CrDetail.class)
	public Response getAll(@PathParam("mietNr") final String mietNr, @PathParam("jahr") final String jahr,
			@HeaderParam("Origin") final String origin, @HeaderParam("Accept-Language") final String acceptLang) {
		String[] langs = acceptLang.split(",");
		Locale locale = Locale.forLanguageTag(langs[0]);
		return Response.ok(service.readCrDetail(mietNr, jahr, locale)).build();
	}

	@PUT
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value = "update the details for the mietNr and jahr", response = Boolean.class)
	public Response updateDetails(CrDetail crDetail, @HeaderParam("Origin") final String origin) {
		return Response.ok(service.updateCrDetail(crDetail)).build();
	}

	@POST
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value = "create the details for the mietNr and jahr", response = Boolean.class)
	public Response createDetails(CrDetail crDetail, @PathParam("mietNr") final String mietNr,
			@PathParam("jahr") final String jahr, @HeaderParam("Origin") final String origin) {
		return Response.ok(service.createCrDetail(crDetail)).build();
	}

	@DELETE
	@Path("/mietNr/{mietNr}/jahr/{jahr}")
	@DisableCaching
	@ApiOperation(value = "delete the details for the mietNr and jahr", response = Boolean.class)
	public Response deleteDetails(@PathParam("mietNr") final String mietNr, @PathParam("jahr") final String jahr,
			@HeaderParam("Origin") final String origin) {
		return Response.ok(service.deleteCrDetail(mietNr, jahr)).build();
	}
}
