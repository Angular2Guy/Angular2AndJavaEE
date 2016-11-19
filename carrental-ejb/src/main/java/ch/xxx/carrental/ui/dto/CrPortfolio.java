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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CrPortfolio")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrPortfolio {
	@XmlElement(required=true)
	private String id;
	@XmlElement(required=false)
	private String bezeichnung;
	@XmlElement(required=false)
	private Integer anzahlPkw;
	@XmlElement(required=false)
	private Integer anzahlLkw;
	@XmlElement(required=false)
	private Integer anzahlTotal;
	@XmlElement(required=false)
	private BigDecimal mieteGeplantPkw;
	@XmlElement(required=false)
	private BigDecimal mieteGeplantLkw;
	@XmlElement(required=false)
	private BigDecimal mieteGeplantTotal;
	@XmlElement(required=false)
	private BigDecimal mieteAbgerechnetPkw;
	@XmlElement(required=false)
	private BigDecimal mieteAbgerechnetLkw;
	@XmlElement(required=false)
	private BigDecimal mieteAbgerechnetTotal;
	
	public CrPortfolio() {
		
	}

	public CrPortfolio(String id, String bezeichnung, Integer anzahlPkw, Integer anzahlLkw, Integer anzahlTotal,
			BigDecimal mieteGeplantPkw, BigDecimal mieteGeplantLkw, BigDecimal mieteGeplantTotal,
			BigDecimal mieteAbgerechnetPkw, BigDecimal mieteAbgerechnetLkw, BigDecimal mieteAbgerechnetTotal) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.anzahlPkw = anzahlPkw;
		this.anzahlLkw = anzahlLkw;
		this.anzahlTotal = anzahlTotal;
		this.mieteGeplantPkw = mieteGeplantPkw;
		this.mieteGeplantLkw = mieteGeplantLkw;
		this.mieteGeplantTotal = mieteGeplantTotal;
		this.mieteAbgerechnetPkw = mieteAbgerechnetPkw;
		this.mieteAbgerechnetLkw = mieteAbgerechnetLkw;
		this.mieteAbgerechnetTotal = mieteAbgerechnetTotal;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Integer getAnzahlPkw() {
		return anzahlPkw;
	}

	public void setAnzahlPkw(Integer anzahlPkw) {
		this.anzahlPkw = anzahlPkw;
	}

	public Integer getAnzahlLkw() {
		return anzahlLkw;
	}

	public void setAnzahlLkw(Integer anzahlLkw) {
		this.anzahlLkw = anzahlLkw;
	}

	public Integer getAnzahlTotal() {
		return anzahlTotal;
	}

	public void setAnzahlTotal(Integer anzahlTotal) {
		this.anzahlTotal = anzahlTotal;
	}

	public BigDecimal getMieteGeplantPkw() {
		return mieteGeplantPkw;
	}

	public void setMieteGeplantPkw(BigDecimal mieteGeplantPkw) {
		this.mieteGeplantPkw = mieteGeplantPkw;
	}

	public BigDecimal getMieteGeplantLkw() {
		return mieteGeplantLkw;
	}

	public void setMieteGeplantLkw(BigDecimal mieteGeplantLkw) {
		this.mieteGeplantLkw = mieteGeplantLkw;
	}	

	public BigDecimal getMieteAbgerechnetPkw() {
		return mieteAbgerechnetPkw;
	}

	public void setMieteAbgerechnetPkw(BigDecimal mieteAbgerechnetPkw) {
		this.mieteAbgerechnetPkw = mieteAbgerechnetPkw;
	}

	public BigDecimal getMieteAbgerechnetLkw() {
		return mieteAbgerechnetLkw;
	}

	public void setMieteAbgerechnetLkw(BigDecimal mieteAbgerechnetLkw) {
		this.mieteAbgerechnetLkw = mieteAbgerechnetLkw;
	}

	public BigDecimal getMieteAbgerechnetTotal() {
		return mieteAbgerechnetTotal;
	}

	public void setMieteAbgerechnetTotal(BigDecimal mieteAbgerechnetTotal) {
		this.mieteAbgerechnetTotal = mieteAbgerechnetTotal;
	}

	public BigDecimal getMieteGeplantTotal() {
		return mieteGeplantTotal;
	}

	public void setMieteGeplantTotal(BigDecimal mieteGeplantTotal) {
		this.mieteGeplantTotal = mieteGeplantTotal;
	}
	
}
