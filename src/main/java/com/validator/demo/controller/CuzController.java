package com.validator.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.validator.demo.service.CuzService;
import com.validator.demo.vo.req.TPCuz0010Req;
import com.validator.demo.vo.req.base.TPCuzRequest;
import com.validator.demo.vo.res.TPCuz0010Res;
import com.validator.demo.vo.res.base.TPCuzResponse;

@RestController
@RequestMapping("/cuz")
public class CuzController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CuzController.class);
	@Autowired
	CuzService cuzService;
	
	@RequestMapping(value="/detail",method = RequestMethod.POST)
	public @ResponseBody TPCuzResponse<TPCuz0010Res> detail(@RequestBody TPCuzRequest<TPCuz0010Req> request){
		//check cuz Test
		return cuzService.getTPCuzDetail(request);
	}
}
