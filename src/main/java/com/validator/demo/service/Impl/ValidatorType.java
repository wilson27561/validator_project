package com.validator.demo.service.Impl;

import com.validator.demo.service.ValidateType;

public class ValidatorType<T> implements ValidateType<T>{
	
	@Override
	public Class<T> getTypeClass(Class<T> classType) {
		return classType;
	}

}
