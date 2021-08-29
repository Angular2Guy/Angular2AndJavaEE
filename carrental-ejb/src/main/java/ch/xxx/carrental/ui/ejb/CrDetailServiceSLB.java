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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.dto.CrLogMsg;
import ch.xxx.carrental.ui.dto.CrLogMsg.LogLevel;
import ch.xxx.carrental.ui.exception.LocalEntityNotFoundException;
import ch.xxx.carrental.ui.exception.LocalValidationException;
import ch.xxx.carrental.ui.model.CrDetailDB;
import ch.xxx.carrental.ui.model.CrPeriodDB;
import ch.xxx.carrental.ui.model.CrPortfolioDB;
import ch.xxx.carrental.ui.service.CrDetailService;
import ch.xxx.carrental.ui.service.CrLoggingService;

@Local(CrDetailService.class)
@Stateless
public class CrDetailServiceSLB implements CrDetailService {
	@EJB
	private CrLoggingService logService;
	@Inject
	private Utils myUtils;
	@EJB
	private CrServerSIB server;
	@PersistenceContext
	private EntityManager em;

	private Converter conv = new Converter();

	@AutoLogging
	@Override
	public CrDetail readCrDetail(String mietNr, String jahr, Locale locale) {
		this.checkForMietNrAndJahr(mietNr, jahr);
		if (this.myUtils.checkForWildflyorWS()) {
			List<CrDetailDB> resultList = em
					.createQuery("select c from CrDetailDB c where c.mietNr=:mietNr and c.jahr=:jahr", CrDetailDB.class)
					.setParameter("mietNr", mietNr).setParameter("jahr", jahr).getResultList();
			if(resultList.isEmpty()) {
				throw new LocalEntityNotFoundException("CrDetail not found.");
			}
			return conv.convert((resultList.get(0)));
		}
		return server.readCrDetail(mietNr, jahr);
	}

	@AutoLogging
	@Override
	public boolean createCrDetail(CrDetail crDetail) {
		this.checkForCrDetail(crDetail);
		if (this.myUtils.checkForWildflyorWS()) {
			CrDetailDB crDetailDB = new CrDetailDB();
			CrPeriodDB crPeriodDB = new CrPeriodDB();
			crDetailDB.getCrPeriods().add(crPeriodDB);
			crPeriodDB.setCrDetail(crDetailDB);
			CrPortfolioDB crPortfolioDB = new CrPortfolioDB();			
			crPeriodDB.getCrPortfolios().add(crPortfolioDB);
			crPortfolioDB.setCrPeriod(crPeriodDB);
			crDetailDB.setMietNr("1");
			Calendar gc = GregorianCalendar.getInstance();
			gc.setTime(crDetail.getCrPeriods().get(0).getFrom());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.logService.logMsg(new CrLogMsg(LogLevel.INFO, sdf.format(crDetail.getCrPeriods().get(0).getFrom())));
			if(gc.get(Calendar.DAY_OF_MONTH) == 31 && gc.get(Calendar.MONTH) == 11) {
				gc.add(Calendar.DAY_OF_YEAR, 1);
			}
			int year = gc.get(Calendar.YEAR);
			crDetailDB.setJahr(Integer.toString(year));
			crPeriodDB.setPeriodFrom(crDetail.getCrPeriods().get(0).getFrom());
			crPeriodDB.setPeriodTo(crDetail.getCrPeriods().get(0).getTo());
			conv.convert(crDetail.getCrPeriods().get(0).getCrPortfolios().get(0), Optional.of(crPortfolioDB));			
			em.persist(crDetailDB);			
			return true;
		}
		return server.createCrDetail(crDetail);
	}

	@AutoLogging
	@Override
	public boolean updateCrDetail(CrDetail crDetail) {
		this.checkForCrDetail(crDetail);
		if (this.myUtils.checkForWildflyorWS()) {
			List<CrDetailDB> resultList = em
					.createQuery("select c from CrDetailDB c where c.mietNr=:mietNr and c.jahr=:jahr", CrDetailDB.class)
					.setParameter("mietNr", crDetail.getMieteNr()).setParameter("jahr", crDetail.getJahr()).getResultList();
			Calendar gc = GregorianCalendar.getInstance();
			gc.setTime(crDetail.getCrPeriods().get(0).getFrom());
			int year = gc.get(Calendar.YEAR);
			resultList.get(0).setJahr(Integer.toString(year));
			return resultList.isEmpty() ? false : conv.convert(crDetail, (resultList.get(0)));
		}
		return server.updateCrDetail(crDetail);
	}

	@AutoLogging
	@Override
	public boolean deleteCrDetail(String mietNr, String jahr) {
		this.checkForMietNrAndJahr(mietNr, jahr);
		if (this.myUtils.checkForWildflyorWS()) {
			List<CrDetailDB> resultList = em
					.createQuery("select c from CrDetailDB c where c.mietNr=:mietNr and c.jahr=:jahr", CrDetailDB.class)
					.setParameter("mietNr", mietNr).setParameter("jahr", jahr).getResultList();
			if(resultList.isEmpty()) {
				return false;
			}
			em.remove(resultList.get(0));
			return true;
		}
		return server.deleteCrDetail(mietNr, jahr);
	}

	private void checkForCrDetail(CrDetail dto) {
		if(dto == null) {
			throw new LocalValidationException("CrDetail is null");
		}
	}
	
	private void checkForMietNrAndJahr(String mietNr, String jahr) {
		if(mietNr == null || jahr == null) {
			throw new LocalValidationException("MietNr or Jahr is null");
		}
	}
}
