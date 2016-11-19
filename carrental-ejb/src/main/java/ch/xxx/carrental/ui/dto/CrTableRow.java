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
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CrTableRow")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrTableRow {
	@XmlElement(required=true)
	private String mietNr;
	@XmlElement(required=true)
	private String jahr;
	@XmlElement(required=false)
	private String status;
	@XmlElement(required=false)
	private String grund;
	@XmlElement(required=false)
	private BigDecimal fahrzeugeTotal;
	@XmlElement(required=false)
	private BigDecimal mieteGeplantTotal;
	@XmlElement(required=false)
	private BigDecimal mieteAbgerechnetTotal;
	@XmlElement(required=false)
	private Integer anzahlPkw;
	@XmlElement(required=false)
	private Integer anzahlLkw;
	@XmlElement(required=false)
	private Date mutatedAt;
	@XmlElement(required=false)
	private String mutatedBy;
	@XmlElement(required=false)
	private boolean mahnstop;
	
	public CrTableRow() {
		
	}

	public CrTableRow(String mietNr, String jahr, String status, String grund, BigDecimal fahrzeugeTotal,
			BigDecimal mieteGeplantTotal, BigDecimal mieteAbgerechnetTotal, Integer anzahlPkw, Integer anzahlLkw,
			Date mutatedAt, String mutatedBy, boolean mahnstop) {
		super();
		this.mietNr = mietNr;
		this.jahr = jahr;
		this.status = status;
		this.grund = grund;
		this.fahrzeugeTotal = fahrzeugeTotal;
		this.mieteGeplantTotal = mieteGeplantTotal;
		this.mieteAbgerechnetTotal = mieteAbgerechnetTotal;
		this.anzahlPkw = anzahlPkw;
		this.anzahlLkw = anzahlLkw;
		this.mutatedAt = mutatedAt;
		this.mutatedBy = mutatedBy;
		this.mahnstop = mahnstop;
	}

	public String getMietNr() {
		return mietNr;
	}

	public void setMietNr(String mietNr) {
		this.mietNr = mietNr;
	}

	public String getJahr() {
		return jahr;
	}

	public void setJahr(String jahr) {
		this.jahr = jahr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGrund() {
		return grund;
	}

	public void setGrund(String grund) {
		this.grund = grund;
	}

	public BigDecimal getFahrzeugeTotal() {
		return fahrzeugeTotal;
	}

	public void setFahrzeugeTotal(BigDecimal fahrzeugeTotal) {
		this.fahrzeugeTotal = fahrzeugeTotal;
	}

	public BigDecimal getMieteGeplantTotal() {
		return mieteGeplantTotal;
	}

	public void setMieteGeplantTotal(BigDecimal mieteGeplantTotal) {
		this.mieteGeplantTotal = mieteGeplantTotal;
	}

	public BigDecimal getMieteAbgerechnetTotal() {
		return mieteAbgerechnetTotal;
	}

	public void setMieteAbgerechnetTotal(BigDecimal mieteAbgerechnetTotal) {
		this.mieteAbgerechnetTotal = mieteAbgerechnetTotal;
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

	public Date getMutatedAt() {
		return mutatedAt;
	}

	public void setMutatedAt(Date mutatedAt) {
		this.mutatedAt = mutatedAt;
	}

	public String getMutatedBy() {
		return mutatedBy;
	}

	public void setMutatedBy(String mutatedBy) {
		this.mutatedBy = mutatedBy;
	}

	public boolean isMahnstop() {
		return mahnstop;
	}

	public void setMahnstop(boolean mahnstop) {
		this.mahnstop = mahnstop;
	}
			
}
