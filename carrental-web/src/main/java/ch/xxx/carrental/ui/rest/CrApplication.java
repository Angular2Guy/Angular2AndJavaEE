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

@ApplicationPath("rest")
public class CrApplication extends Application {
	
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
		return classes;
	}
		
}
