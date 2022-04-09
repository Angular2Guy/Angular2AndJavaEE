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
package ch.xxx.carrrental.ui.rest.model;

import java.math.BigDecimal;
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
