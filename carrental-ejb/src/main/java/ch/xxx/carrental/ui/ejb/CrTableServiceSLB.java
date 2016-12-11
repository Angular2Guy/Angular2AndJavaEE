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

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.xxx.carrental.ui.dto.CrTableRow;
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
	public List<CrTableRow> readCrRowsByMiete(String mieteNr) {
//		List<CrDetailDB> resultList = em.createQuery("select * from CrDetailDB c where c.mieteNr=:mieteNr", CrDetailDB.class).setParameter("mieteNr", mieteNr).getResultList();
//		List<CrTableRow> rows = new ArrayList<CrTableRow>();
//		resultList.forEach(d -> d.getCrPeriods().forEach(per -> per.getCrPortfolios().forEach(port -> rows.add(conv.convertTableRow(port, d.getJahr(), d.getMietNr())))));
//		for(CrDetailDB res: resultList) {
//			for(CrPeriodDB p: res.getCrPeriods()) {
//				for(CrPortfolioDB port: p.getCrPortfolios()) {
//					CrTableRow row = conv.convertTableRow(port, res.getJahr(), res.getMietNr());
//					rows.add(row);
//				}
//			}
//		}
//		return rows;
		return server.readCrRowsByMiete(mieteNr);		
	}

}
