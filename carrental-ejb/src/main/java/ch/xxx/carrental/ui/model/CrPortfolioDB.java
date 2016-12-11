package ch.xxx.carrental.ui.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CrPortfolio")
public class CrPortfolioDB {
	
	@Id
	@GeneratedValue
	private Long id;
	private String bezeichnung;
	private String status;
	private String grund;
	private Integer anzahlPkw;
	private Integer anzahlLkw;
	private Integer anzahlTotal;
	private BigDecimal mieteGeplantPkw;
	private BigDecimal mieteGeplantLkw;
	private BigDecimal mieteGeplantTotal;
	private BigDecimal mieteAbgerechnetPkw;
	private BigDecimal mieteAbgerechnetLkw;
	private BigDecimal mieteAbgerechnetTotal;
	@ManyToOne
	private CrPeriodDB crPeriod;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public BigDecimal getMieteGeplantTotal() {
		return mieteGeplantTotal;
	}
	public void setMieteGeplantTotal(BigDecimal mieteGeplantTotal) {
		this.mieteGeplantTotal = mieteGeplantTotal;
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
	public CrPeriodDB getCrPeriod() {
		return crPeriod;
	}
	public void setCrPeriod(CrPeriodDB crPeriod) {
		this.crPeriod = crPeriod;
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
	
}
