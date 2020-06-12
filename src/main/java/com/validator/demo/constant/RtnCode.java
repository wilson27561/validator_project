package com.validator.demo.constant;

public enum RtnCode {

	C0000("C0000","成功"),
	
	C0099("C0099","系統錯誤"),
	
	C0098("C0098","欄位{0}錯誤");
	
	private String rtnCode;
	private String rtnMsg;
	
	private RtnCode(String rtnCode, String rtnMsg) {
		this.rtnCode = rtnCode;
		this.rtnMsg = rtnMsg;
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
	
	public static String getRtnMsgByRtnCode(String rtnCode) {
		for(RtnCode r : RtnCode.values()) {
			if(r.rtnCode.equals(rtnCode)) {
				return r.rtnMsg;
			}
		}
		return "";
	}
	

}
