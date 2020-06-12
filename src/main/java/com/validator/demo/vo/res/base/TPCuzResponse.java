package com.validator.demo.vo.res.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TPCuzResponse<T extends TPCuzResBody> {

	@JsonProperty("header")
	private TPCuzResHeader tpCuzResHeader;
	
	@JsonProperty("body")
	private T body;
	
	public TPCuzResponse() {	
	}
	
	public TPCuzResponse(TPCuzResHeader tpCuzResHeader) {
		super();
		this.tpCuzResHeader = tpCuzResHeader;
	}
	

	public TPCuzResponse(T body) {
		super();
		this.body = body;
	}


	public TPCuzResHeader getTpCuzResHeader() {
		return tpCuzResHeader;
	}

	public void setTpCuzResHeader(TPCuzResHeader tpCuzResHeader) {
		this.tpCuzResHeader = tpCuzResHeader;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
