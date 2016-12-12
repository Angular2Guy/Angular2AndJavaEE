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

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.model.CrDetailDB;
import ch.xxx.carrental.ui.model.CrPeriodDB;
import ch.xxx.carrental.ui.model.CrPortfolioDB;
import ch.xxx.carrental.ui.service.CrTableService;

@Local(CrTableService.class)
@Stateless
public class CrTableServiceSLB implements CrTableService {
	
	@EJB
	private CrServerSIB server;
	@PersistenceContext
	private EntityManager em;
	private Converter conv = new Converter();
	
	@AutoLogging
	@Override
	public List<CrTableRow> readCrRowsByMiete(String mietNr) {
		List<CrDetailDB> resultList = em.createQuery("select c from CrDetailDB c where c.mietNr=:mietNr", CrDetailDB.class).setParameter("mietNr", mietNr).getResultList();
		List<CrTableRow> rows = new ArrayList<CrTableRow>();
		resultList.forEach(d -> d.getCrPeriods().forEach(per -> per.getCrPortfolios().forEach(port -> rows.add(conv.convertTableRow(port, d.getJahr(), d.getMietNr())))));
		return rows;
//		return server.readCrRowsByMiete(mieteNr);		
	}

}
