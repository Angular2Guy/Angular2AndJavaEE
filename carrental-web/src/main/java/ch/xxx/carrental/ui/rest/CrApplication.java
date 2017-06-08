package ch.xxx.carrental.ui.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.dto.CrMessage;
import ch.xxx.carrental.ui.dto.CrPeriod;
import ch.xxx.carrental.ui.dto.CrPortfolio;
import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.rest.model.CrDetailResource;
import ch.xxx.carrental.ui.rest.model.CrTableResource;
import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("rest")
public class CrApplication extends Application {
	
	public CrApplication() {
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/rest");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(CrPortfolio.class);
		classes.add(CrDetail.class);
		classes.add(CrMessage.class);
		classes.add(CrPeriod.class);
		classes.add(CrTableRow.class);
		classes.add(CrTableResource.class);
		classes.add(CrDetailResource.class);
		classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		return classes;
	}
		
}
