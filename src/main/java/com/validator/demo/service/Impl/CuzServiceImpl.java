package com.validator.demo.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.validator.demo.aop.ValidatorAspect;
import com.validator.demo.service.CuzService;
import com.validator.demo.vo.req.TPCuz0010Req;
import com.validator.demo.vo.req.base.TPCuzRequest;
import com.validator.demo.vo.res.TPCuz0010Res;
import com.validator.demo.vo.res.base.TPCuzResBody;
import com.validator.demo.vo.res.base.TPCuzResHeader;
import com.validator.demo.vo.res.base.TPCuzResponse;

@Service
public class CuzServiceImpl implements CuzService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CuzServiceImpl.class);
	
	@Override
	public TPCuzResponse<TPCuz0010Res> getTPCuzDetail(TPCuzRequest<TPCuz0010Req> request) {
		
		LOGGER.info("cuz Id "+request.getBody().getCuzId());
		
		TPCuz0010Res tpCuz0010Res = new TPCuz0010Res();
		tpCuz0010Res.setRtnCuz("get Cuz Success");
		
		
		TPCuzResponse<TPCuz0010Res> tpCuzResponse = new TPCuzResponse<>();
		TPCuzResHeader tpCuzResHeader = new TPCuzResHeader();
		tpCuzResHeader.setRtnCode("C0001");
		tpCuzResHeader.setRtnCode("OK");
		
		tpCuzResponse.setBody(tpCuz0010Res);
		tpCuzResponse.setTpCuzResHeader(tpCuzResHeader);
		
		return tpCuzResponse;
	}




}
