package ch.xxx.carrental.ui.ejb;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.xxx.carrental.ui.dto.CrDetail;
import ch.xxx.carrental.ui.dto.CrMessage;
import ch.xxx.carrental.ui.dto.CrPeriod;
import ch.xxx.carrental.ui.dto.CrPortfolio;
import ch.xxx.carrental.ui.dto.CrTableRow;
import ch.xxx.carrental.ui.model.CrDetailDB;
import ch.xxx.carrental.ui.model.CrMessageDB;
import ch.xxx.carrental.ui.model.CrPeriodDB;
import ch.xxx.carrental.ui.model.CrPortfolioDB;

public class Converter {
	
	public CrDetail convert(CrDetailDB db) {
		CrDetail crDetail = new CrDetail();
		crDetail.setJahr(db.getJahr());
		crDetail.setMietNr(db.getMietNr().toString());
		crDetail.setCrMessages(db.getCrMessages() == null ? null : convertMsgList(db.getCrMessages()));
		crDetail.setCrPeriods(db.getCrPeriods() == null ? null : convertPeriodList(db.getCrPeriods()));
		return crDetail;
	}
	
	private List<CrMessage> convertMsgList(List<CrMessageDB> db) {
		List<CrMessage> messages = db.stream().map(msg -> convert(msg)).collect(Collectors.toList());
		return messages;
	}
	
	private CrMessage convert(CrMessageDB db) {
		CrMessage msg = new CrMessage();
		msg.setId(db.getId().toString());
		msg.setMsg(db.getMsg());
		msg.setMsgType(db.getMsgType());
		return msg;
	}
	
	private List<CrPeriod> convertPeriodList(List<CrPeriodDB> db) {
		List<CrPeriod> periods = db.stream().map(p -> convert(p)).collect(Collectors.toList());
		return periods;
	}
	
	private CrPeriod convert(CrPeriodDB db) {
		CrPeriod period = new CrPeriod();
		period.setFrom(db.getPeriodFrom());
		period.setTo(db.getPeriodTo());
		period.setId(db.getId().toString());
		period.setCrPortfolios(db.getCrPortfolios() == null ? null: convertPortList(db.getCrPortfolios()));
		return period;
	}
	
	private List<CrPortfolio> convertPortList(List<CrPortfolioDB> db) {
		List<CrPortfolio> portfolios = db.stream().map(p -> convert(p)).collect(Collectors.toList());
		return portfolios;
	}
	
	private CrPortfolio convert(CrPortfolioDB db) {
		CrPortfolio portfolio = new CrPortfolio();
		portfolio.setAnzahlLkw(db.getAnzahlLkw());
		portfolio.setAnzahlPkw(db.getAnzahlPkw());
		portfolio.setAnzahlTotal(db.getAnzahlTotal());
		portfolio.setBezeichnung(db.getBezeichnung());
		portfolio.setId(db.getId().toString());
		portfolio.setMieteAbgerechnetLkw(db.getMieteAbgerechnetLkw());
		portfolio.setMieteAbgerechnetPkw(db.getMieteAbgerechnetPkw());
		portfolio.setMieteAbgerechnetTotal(db.getMieteAbgerechnetTotal());
		portfolio.setMieteGeplantLkw(db.getMieteGeplantLkw());
		portfolio.setMieteGeplantPkw(db.getMieteGeplantPkw());
		portfolio.setMieteGeplantTotal(db.getMieteGeplantTotal());
		return portfolio;
	}
	
	public CrTableRow convertTableRow(CrPortfolioDB db, String jahr, Long mietNr) {
		CrTableRow row = new CrTableRow();
		row.setAnzahlLkw(db.getAnzahlLkw());
		row.setAnzahlPkw(db.getAnzahlPkw());
		row.setFahrzeugeTotal(db.getAnzahlTotal());
		row.setGrund(db.getGrund());
		row.setStatus(db.getStatus());
		row.setJahr(jahr);
		row.setMietNr(mietNr.toString());
		row.setMutatedAt(new Date());
		row.setMutatedBy("xxx");
		row.setMieteAbgerechnetTotal(db.getMieteAbgerechnetTotal());
		row.setMieteGeplantTotal(db.getMieteGeplantTotal());
		return row;
	}
}
