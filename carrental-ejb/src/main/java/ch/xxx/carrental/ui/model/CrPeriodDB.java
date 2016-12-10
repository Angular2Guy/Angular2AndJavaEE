package ch.xxx.carrental.ui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CrPeriod")
public class CrPeriodDB {
	@Id
	@GeneratedValue
	private Long id;
	private Date periodFrom;
	private Date periodTo;
	@OneToMany(mappedBy="crPeriod",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<CrPortfolioDB> crPortfolios = new ArrayList<>();
	@ManyToOne
	private CrDetailDB crDetail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}
	public Date getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}
	public List<CrPortfolioDB> getCrPortfolios() {
		return crPortfolios;
	}
	public void setCrPortfolios(List<CrPortfolioDB> crPortfolios) {
		this.crPortfolios = crPortfolios;
	}
	public CrDetailDB getCrDetail() {
		return crDetail;
	}
	public void setCrDetail(CrDetailDB crDetail) {
		this.crDetail = crDetail;
	}
	
}
