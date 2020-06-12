package com.validator.demo.service;

import com.validator.demo.vo.req.TPCuz0010Req;
import com.validator.demo.vo.req.base.TPCuzRequest;
import com.validator.demo.vo.res.TPCuz0010Res;
import com.validator.demo.vo.res.base.TPCuzResponse;

public interface CuzService {	
	public TPCuzResponse<TPCuz0010Res> getTPCuzDetail(TPCuzRequest<TPCuz0010Req> request);
}
