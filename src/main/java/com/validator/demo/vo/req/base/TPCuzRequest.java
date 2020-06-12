package com.validator.demo.vo.req.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.validator.demo.annotation.Validator;

public class TPCuzRequest<T extends TPCuzReqBody> {
     
	@JsonProperty("header")
	@Validator(parameterType="header")
	private TPCuzReqHeader tpCuzReqHeader;
	
	
	@JsonProperty("body")
	@Validator(parameterType="body")
	private T body;

	public TPCuzReqHeader getTpCuzReqHeader() {
		return tpCuzReqHeader;
	}

	public void setTpCuzReqHeader(TPCuzReqHeader tpCuzReqHeader) {
		this.tpCuzReqHeader = tpCuzReqHeader;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
