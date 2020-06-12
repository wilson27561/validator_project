package com.validator.demo.vo.res.base;

import java.util.Date;

public class TPCuzResHeader {
	
    private Date txDate;
	
	private String txID;
	
	private String cID;
	
	private String rtnCode;
	
	private String rtnMsg;

	public Date getTxDate() {
		return txDate;
	}

	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}

	public String getTxID() {
		return txID;
	}

	public void setTxID(String txID) {
		this.txID = txID;
	}

	public String getcID() {
		return cID;
	}

	public void setcID(String cID) {
		this.cID = cID;
	}

	public String getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

}
