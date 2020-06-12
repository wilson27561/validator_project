package com.validator.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.validator.demo.annotation.Validator;

public class Certificate {
	
	@JsonProperty("certType")
	@Validator(parameterType="Param",pattern="^[A-Za-z0-9]+$")
	private String certType;
	@JsonProperty("accountNumber")
	@Validator(parameterType="Param")
	private String accountNumber;
	@JsonProperty("certSerialNumber")
	@Validator(parameterType="Param")
	private String certSerialNumber;

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCertSerialNumber() {
		return certSerialNumber;
	}

	public void setCertSerialNumber(String certSerialNumber) {
		this.certSerialNumber = certSerialNumber;
	}
	
	


}
