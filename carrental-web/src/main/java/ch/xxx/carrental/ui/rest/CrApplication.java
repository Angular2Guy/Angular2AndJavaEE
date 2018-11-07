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
package ch.xxx.carrental.ui.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.dto.CrLogMsg;
import ch.xxx.carrental.ui.dto.CrMessage;
import ch.xxx.carrental.ui.dto.CrPeriod;
import ch.xxx.carrental.ui.dto.CrPortfolio;
import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.rest.model.CrDetailResource;
import ch.xxx.carrental.ui.rest.model.CrLogResource;
import ch.xxx.carrental.ui.rest.model.CrTableResource;
import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("rest")
public class CrApplication extends Application {
	
	public CrApplication() {
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.5.10");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/carrental-web/rest");
        beanConfig.setResourcePackage("ch.xxx.carrental.ui.rest.model");
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
		classes.add(CrLogMsg.class);
		classes.add(CrTableResource.class);
		classes.add(CrDetailResource.class);
		classes.add(CrLogResource.class);
		classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		return classes;
	}
		
}
