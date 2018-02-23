package ch.xxx.carrrental.ui.rest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.rest.model.CrTableResource;
import ch.xxx.carrental.ui.service.CrTableService;


public class CrTableResourceTest {
	
	CrTableService service;
	
	@Before
	public void init() {
		this.service = Mockito.mock(CrTableService.class);
		Mockito.when(this.service.readCrRowsByMiete(Mockito.anyString(), Mockito.any(Locale.class))).thenReturn(this.initCrTable());
	}

	private  List<CrTableRow> initCrTable() {
		Calendar from = Calendar.getInstance();
		from.set(2015, 0, 1);
		Calendar to = Calendar.getInstance();
		to.set(2015, 11,31);
		CrTableRow lsdTableRow1 = new CrTableRow("1", "2015", "Aktiv", "Geschaeflich", 20, BigDecimal.valueOf(30000), BigDecimal.valueOf(20000), 10, 12, from.getTime(), "p12345", true);
		CrTableRow lsdTableRow2 = new CrTableRow("1", "2016", "Passiv", "Privat", null, null, null, null, null, from.getTime(), "p12345", false);
		return Arrays.asList(lsdTableRow1, lsdTableRow2);
	}
	
	@Test
	public void testGetAll() {
		CrTableResource testee = new CrTableResource(this.service);
		Response resp = testee.getAll("1", "en");		
		Assert.assertEquals(200, resp.getStatus());
	}
}
