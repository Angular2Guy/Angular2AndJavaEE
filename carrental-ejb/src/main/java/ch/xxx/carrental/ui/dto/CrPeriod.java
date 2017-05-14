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
package ch.xxx.carrental.ui.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CrPeriod")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrPeriod {
	@XmlElement(required=false)
	private Long id;
	@XmlElement(required=false)
	private Date from;
	@XmlElement(required=false)
	private Date to;
	@XmlElement(required=false)
	private List<CrPortfolio> crPortfolios = new ArrayList<>();
	
	public CrPeriod() {
		
	}
	
	public CrPeriod(Long id, Date from, Date to) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}

	public List<CrPortfolio> getCrPortfolios() {
		return crPortfolios;
	}

	public void setCrPortfolios(List<CrPortfolio> crPortfolios) {
		this.crPortfolios = crPortfolios;
	}

}
