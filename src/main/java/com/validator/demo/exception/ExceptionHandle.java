package com.validator.demo.exception;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.validator.demo.vo.res.base.TPCuzResHeader;
import com.validator.demo.vo.res.base.TPCuzResponse;

@ControllerAdvice
public class ExceptionHandle {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler
	public ResponseEntity<Object> ReqValidatorExceptionHandle(ReqValidatorException ex,HttpServletRequest request){
		LOGGER.error("req validator error ",ex);
		TPCuzResHeader tpCuzResHeader = new TPCuzResHeader();
		tpCuzResHeader.setRtnCode(ex.getErrCode());
		tpCuzResHeader.setRtnMsg(MessageFormat.format(ex.getErrMsg(), ex.getArgs()));

		return ResponseEntity.status(HttpStatus.OK).body(new TPCuzResponse<>(tpCuzResHeader));
	}
	
	
}
