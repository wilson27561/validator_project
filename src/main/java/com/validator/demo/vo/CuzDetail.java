package com.validator.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.validator.demo.annotation.Validator;

public class CuzDetail {
	
	@JsonProperty("email")
	@Validator(parameterType="Param")
	private String email;
	@JsonProperty("phone")
	@Validator(parameterType="Param")
	private String phone;
	@JsonProperty("enName")
	@Validator(parameterType="Param")
	private String enName;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}

}
