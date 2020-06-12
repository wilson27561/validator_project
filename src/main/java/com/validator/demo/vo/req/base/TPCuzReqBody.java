package com.validator.demo.vo.req.base;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TPCuzReqBody {
	
	@JsonProperty("cuzId")
	private String cuzId;
	
	@JsonProperty("actionType")
	private String actionType;
	
	@JsonProperty("createSDate")
	private String createSDate;
	
	@JsonProperty("createEDate")
	private String createEDate;
	

	
	public String getCuzId() {
		return cuzId;
	}
	public void setCuzId(String cuzId) {
		this.cuzId = cuzId;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getCreateSDate() {
		return createSDate;
	}
	public void setCreateSDate(String createSDate) {
		this.createSDate = createSDate;
	}
	public String getCreateEDate() {
		return createEDate;
	}
	public void setCreateEDate(String createEDate) {
		this.createEDate = createEDate;
	}
	
	

}
