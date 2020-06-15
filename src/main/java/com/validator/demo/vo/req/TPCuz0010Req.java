package com.validator.demo.vo.req;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.validator.demo.annotation.Validator;
import com.validator.demo.vo.Certificate;
import com.validator.demo.vo.CuzDetail;
import com.validator.demo.vo.req.base.TPCuzReqBody;

public class TPCuz0010Req extends TPCuzReqBody {


	@JsonProperty("cuzId")
//	,pattern="^[A-Za-z0-9]+$"
	@Validator(parameterType="Param",basicPattern="nationalIndividual")
	private String cuzId;
	@JsonProperty("actionType")
	@Validator(parameterType="Param")
	private String actionType;
	@JsonProperty("createSDate")
	@Validator(parameterType="Param")
	private String createSDate;
	@JsonProperty("createEDate")
	@Validator(parameterType="Param")
	private String createEDate;
	@JsonProperty("cuzDetail")
	@Validator(parameterType="Object")
	private CuzDetail cuzDetail;
	
	@JsonProperty("certList")
	@Validator(parameterType="List")
	private List<Certificate> certList;
	

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

	public CuzDetail getCuzDetail() {
		return cuzDetail;
	}

	public void setCuzDetail(CuzDetail cuzDetail) {
		this.cuzDetail = cuzDetail;
	}

	public List<Certificate> getCertList() {
		return certList;
	}

	public void setCertList(List<Certificate> certList) {
		this.certList = certList;
	}
}
