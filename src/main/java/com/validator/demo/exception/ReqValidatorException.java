package com.validator.demo.exception;

import com.validator.demo.constant.RtnCode;

public class ReqValidatorException extends RuntimeException {
	
	private ReqValidatorException(RtnCode rtnCode) {
		this.errCode = rtnCode.getRtnCode();
		this.errMsg =  rtnCode.getRtnMsg();
	}
	private ReqValidatorException(RtnCode rtnCode, String [] args) {
		this.errCode = rtnCode.getRtnCode();
		this.errMsg = rtnCode.getRtnMsg();
		this.args = args;
	}
	public static ReqValidatorException createByErrCode(RtnCode rtnCode) {
		return new ReqValidatorException(rtnCode);
	}
	public static ReqValidatorException createByErrCodeAndArgs(RtnCode rtnCode,String[] args) {
		return new ReqValidatorException(rtnCode,args);
	}

	private String errCode;
	private String errMsg;
	private String[] args;
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
}
