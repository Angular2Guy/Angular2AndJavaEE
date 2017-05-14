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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CrDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrDetail {
	@XmlElement(required=false)
	private Long id;
	@XmlElement(required=true)
	private boolean	changeable;
	@XmlElement(required=true)
	private String mietNr;
	@XmlElement(required=true)
	private String jahr;
	@XmlElement(required=false)
	private CrTableRow crTableRow;
	@XmlElement(required=false)
	private List<CrPeriod> crPeriods = new ArrayList<>();
	@XmlElement(required=false)
	private List<CrMessage> crMessages = new ArrayList<>();
	
	public CrDetail() {
		
	}
	
	public CrDetail(Long id, boolean changeable, String mietNr, String jahr, CrTableRow lsdTableRow) {
		super();
		this.id = id;
		this.changeable = changeable;
		this.mietNr = mietNr;
		this.jahr = jahr;
		this.crTableRow = lsdTableRow;
	}
		
	public String getJahr() {
		return jahr;
	}
	public void setJahr(String jahr) {
		this.jahr = jahr;
	}	

	public String getMietNr() {
		return mietNr;
	}

	public void setMietNr(String mietNr) {
		this.mietNr = mietNr;
	}

	public CrTableRow getCrTableRow() {
		return crTableRow;
	}

	public void setCrTableRow(CrTableRow crTableRow) {
		this.crTableRow = crTableRow;
	}

	public List<CrPeriod> getCrPeriods() {
		return crPeriods;
	}

	public void setCrPeriods(List<CrPeriod> crPeriods) {
		this.crPeriods = crPeriods;
	}

	public List<CrMessage> getCrMessages() {
		return crMessages;
	}

	public void setCrMessages(List<CrMessage> crMessages) {
		this.crMessages = crMessages;
	}

	public boolean isChangeable() {
		return changeable;
	}

	public void setChangeable(boolean changeable) {
		this.changeable = changeable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
