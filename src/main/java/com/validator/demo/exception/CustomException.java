package com.validator.demo.exception;



public class CustomException extends RuntimeException{

	public static CustomException createByErrCode(String errMsg) {
		return new CustomException(errMsg);
	}
	
	private CustomException(String errMsg) {
		this.errMsg =  errMsg;
	}
	
	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
