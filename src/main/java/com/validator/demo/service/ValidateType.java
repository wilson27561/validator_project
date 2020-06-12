package com.validator.demo.service;

public interface ValidateType<T> {
	
     Class<T> getTypeClass(Class<T> classType);

}
