package ch.xxx.carrental.ui.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CrMessage")
public class CrMessageDB {
	@Id
	@GeneratedValue
	private Long id;
	private String msgType;
	private String msg;
	@ManyToOne
	private CrDetailDB crDetail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public CrDetailDB getCrDetail() {
		return crDetail;
	}
	public void setCrDetail(CrDetailDB crDetail) {
		this.crDetail = crDetail;
	}
	
}
