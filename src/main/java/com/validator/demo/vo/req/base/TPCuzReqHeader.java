package com.validator.demo.vo.req.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.validator.demo.annotation.Validator;

public class TPCuzReqHeader {
	
	private String txDate;
	@Validator(pattern = "^.[A-Za-z0-9]+$")
	@JsonProperty("txID")
	private String txID;

	private String cID;

	public String getTxDate() {
		return txDate;
	}

	public void setTxDate(String txDate) {
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

}
