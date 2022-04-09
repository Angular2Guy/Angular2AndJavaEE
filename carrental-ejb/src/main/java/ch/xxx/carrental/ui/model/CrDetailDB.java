package ch.xxx.carrental.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CrDetail")
public class CrDetailDB {
	
	@Id
	@GeneratedValue
	private Long id;
	private String mietNr;
	private String jahr;
	@OneToMany(mappedBy="crDetail",fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CrPeriodDB> crPeriods = new ArrayList<>();
	@OneToMany(mappedBy="crDetail",fetch=FetchType.LAZY,cascade=CascadeType.ALL, orphanRemoval=true)
	private List<CrMessageDB> crMessages = new ArrayList<>();
	
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
	public List<CrPeriodDB> getCrPeriods() {
		return crPeriods;
	}
	public void setCrPeriods(List<CrPeriodDB> crPeriods) {
		this.crPeriods = crPeriods;
	}
	public List<CrMessageDB> getCrMessages() {
		return crMessages;
	}
	public void setCrMessages(List<CrMessageDB> crMessages) {
		this.crMessages = crMessages;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
