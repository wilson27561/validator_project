package com.validator.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.validator.demo.exception.ReqValidatorException;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {

	String parameterType() default "";

	String pattern() default "";

	String basicPattern() default "";

	String returnCode() default "欄位檢核錯誤";

	Class<?> classes() default ReqValidatorException.class;

}
