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

import java.util.Locale;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.rest.model.CrDetailResource;
import ch.xxx.carrental.ui.service.CrDetailService;

public class CrDetailResourceTest {
    CrDetailService service;
	
	@Before
	public void init() {
		this.service = Mockito.mock(CrDetailService.class);
		Mockito.when(this.service.readCrDetail(Mockito.anyString(), Mockito.anyString(), Mockito.any(Locale.class))).thenReturn(new CrDetail());
		Mockito.when(this.service.createCrDetail(Mockito.any(CrDetail.class))).thenReturn(true);
		Mockito.when(this.service.deleteCrDetail(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		Mockito.when(this.service.updateCrDetail(Mockito.any(CrDetail.class))).thenReturn(true);
	}
	
	@Test
	public void readTest() {
		CrDetailResource testee = new CrDetailResource(this.service);
		Response resp = testee.getAll("1", "2015", "en");
		Assert.assertEquals(200, resp.getStatus());
	}
	
	@Test
	public void createTest() {
		CrDetailResource testee = new CrDetailResource(this.service);
		Response resp = testee.createDetails(new CrDetail(), "1", "2015");
		Assert.assertEquals(200, resp.getStatus());
	}
	
	@Test
	public void deleteTest() {
		CrDetailResource testee = new CrDetailResource(this.service);		
		Response resp = testee.deleteDetails("1", "2015");
		Assert.assertEquals(200, resp.getStatus());
	}
	
	@Test
	public void updateTest() {
		CrDetailResource testee = new CrDetailResource(this.service);
		Response resp = testee.updateDetails(new CrDetail());		
		Assert.assertEquals(200, resp.getStatus());
	}
}
