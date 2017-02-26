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
package ch.xxx.carrental.ui.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.dto.CrMessage;
import ch.xxx.carrental.ui.dto.CrPeriod;
import ch.xxx.carrental.ui.dto.CrPortfolio;
import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.dto.MsgType;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class CrServerSIB {
	private List<CrTableRow> crTableRows = new ArrayList<>();
	private List<CrDetail> crDetails = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		Calendar from = Calendar.getInstance();
		from.set(2015, 0, 1);
		Calendar to = Calendar.getInstance();
		to.set(2015, 11,31);
		initCrTable(from, to);
		initCrDetail();
	}

	private void initCrDetail() {
		Calendar from = Calendar.getInstance();
		from.set(2015, 0, 1);
		Calendar to = Calendar.getInstance();
		to.set(2015, 11,31);
		CrDetail crDetail1 = new CrDetail(false,"1","2015", crTableRows.get(0));
		CrMessage crMessage1 = new CrMessage("1", MsgType.WARNING.toString(), "Warnung: Dies ist eine Warnung vom Server.");
		crDetail1.getCrMessages().add(crMessage1);
		CrPeriod crPeriod1 = new CrPeriod("1", from.getTime(), to.getTime());
		CrPortfolio crPortfolio = new CrPortfolio("1","Bezeichnung", 10, 10, 20, BigDecimal.valueOf(1000000), BigDecimal.valueOf(1000000), BigDecimal.valueOf(1000000), BigDecimal.valueOf(1000000), BigDecimal.valueOf(500000), BigDecimal.valueOf(2000000));
		crPeriod1.getCrPortfolios().add(crPortfolio);
		crDetail1.getCrPeriods().add(crPeriod1);
		
		from = Calendar.getInstance();
		from.set(2016, 0, 1);
		to = Calendar.getInstance();
		to.set(2016, 11,31);
		CrDetail crDetail2 = new CrDetail(false, "1","2016", crTableRows.get(1));
		CrMessage crMessage2 = new CrMessage("1", MsgType.ERROR.toString(), "Error: Dies ist ein Error vom Server.");
		crDetail2.getCrMessages().add(crMessage2);
		CrPeriod crPeriod2 = new CrPeriod("1", from.getTime(), to.getTime());
		CrPortfolio crPortfolio2 = new CrPortfolio("1", "Bezeichnung", null, null, null, BigDecimal.valueOf(1000000), BigDecimal.valueOf(1000000), BigDecimal.valueOf(1000000), null, null, null);
		crPeriod2.getCrPortfolios().add(crPortfolio2);
		crDetail2.getCrPeriods().add(crPeriod2);
		crDetails.addAll(Arrays.asList(crDetail1, crDetail2));
	}

	private void initCrTable(Calendar from, Calendar to) {
		CrTableRow lsdTableRow1 = new CrTableRow("1", "2015", "Aktiv", "Geschaeflich", 20, BigDecimal.valueOf(30000), BigDecimal.valueOf(20000), 10, 12, from.getTime(), "p12345", true);
		CrTableRow lsdTableRow2 = new CrTableRow("1", "2016", "Passiv", "Privat", null, null, null, null, null, from.getTime(), "p12345", false);
		crTableRows.addAll(Arrays.asList(lsdTableRow1, lsdTableRow2));
	}
	
	public List<CrTableRow> readCrRowsByMiete(final String mietNr) {
		List<CrTableRow> rows = crTableRows.stream().filter(r -> r.getMietNr().equals(mietNr)).collect(Collectors.toList());
		return rows;
	}
	
	public CrDetail readCrDetail(String mietNr, String jahr) {
		Optional<CrDetail> optional = crDetails.stream().filter(d -> d.getMietNr().equals(mietNr) && d.getJahr().equals(jahr)).findFirst();		
		return optional.isPresent() ? optional.get() : null;
	}
	
	public boolean createCrDetail(CrDetail crDetail) {
		return crDetails.add(crDetail);
	}

	public boolean updateCrDetail(CrDetail lsdDetail) {
		Optional<CrDetail> first = crDetails.stream().filter(d -> d.getMietNr().equals(lsdDetail.getMietNr()) && d.getJahr().equals(lsdDetail.getJahr())).findFirst();		
		if(first.isPresent()) {
			return crDetails.remove(first.get()) && crDetails.add(lsdDetail);  
		}
		return false;
	}

	public boolean deleteCrDetail(String mietNr, String jahr) {
		Optional<CrDetail> first = crDetails.stream().filter(d -> d.getMietNr().equals(mietNr) && d.getJahr().equals(jahr)).findFirst();
		if(first.isPresent()) {
			return crDetails.remove(first.get());
		}
		return false;
	}
}
