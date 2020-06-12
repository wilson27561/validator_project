package com.validator.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {
	
	String parameterType() default "";

	String pattern() default "";
	
	String returnCode() default "欄位檢核錯誤";
	
	String name() default "";
}
