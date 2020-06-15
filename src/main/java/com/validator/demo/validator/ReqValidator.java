package com.validator.demo.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.validator.demo.annotation.Validator;
import com.validator.demo.aop.ValidatorAspect;
import com.validator.demo.constant.RtnCode;
import com.validator.demo.constant.ValidatorConst;
import com.validator.demo.exception.ReqValidatorException;
import com.validator.demo.util.IdNumberUtil;
import com.validator.demo.util.PojoUtil;

@Component
public class ReqValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);

	public void validate(Object inputObj) throws IllegalArgumentException, IllegalAccessException, Exception {
		Field[] declaredFields = inputObj.getClass().getDeclaredFields();
		Map<String, Method> methodMap = PojoUtil.getMethodsOfObj(inputObj);
		for (Field field : declaredFields) {
			Method method = getFieldMethod(field, methodMap);
			// no getter don't validate
			if (method == null) {
				continue;
			}
			Validator annotation = PojoUtil.getAnnotation(field);
			if (annotation != null) {
				Object fieldValue = PojoUtil.invokeMethod(method, inputObj);
				// 集合
				if (annotation.parameterType().equals(ValidatorConst.LIST)) {
					checkList(field, fieldValue);
				}
				// 物件
				if (annotation.parameterType().equals(ValidatorConst.OBJECT)) {
					Class clazz = Class.forName(getClassName(field.getType().toString()));
					String objectMethodName = "get" + field.getName().toLowerCase();
					Method objectmethod = methodMap.get(objectMethodName);
					Object objectField = PojoUtil.invokeMethod(objectmethod, inputObj);
					Object objAsType = clazz.cast(objectField);
					this.validate(objAsType);
				}
				// 參數
				if (annotation.parameterType().equals(ValidatorConst.PARAM)) {
					checkPattern(annotation, fieldValue, field.getName());
					checkBasicPattern(annotation, fieldValue, field.getName());
				}
			}
		}
	}

	private void checkList(Field field, Object fieldValue) throws Exception {
		if (Iterable.class.isAssignableFrom(field.getType())) {
			for (Object iteratedObj : (Iterable) fieldValue) {
				this.validate(iteratedObj);
			}
		} else {
			this.validate(fieldValue);
		}
	}

	private void checkBasicPattern(Validator annotation, Object fieldValue, String fieldName) {
		String type = annotation.basicPattern();
		// 確認Type
		if (StringUtils.isNotBlank(type)) {
			// 不等於字串
			if (!isValidString(fieldValue)) {
				throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
			}
			if (ValidatorConst.NATIONAL_INDIVIDUAL.equals(type)) {
				if (!IdNumberUtil.isValidNationalIndividual((String) fieldValue)) {
					throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
				}
			}
			if (ValidatorConst.NATIONAL_LEGAL_PERSON.equals(type)) {
				if (!IdNumberUtil.isValidNationalLegalPerson((String) fieldValue)) {
					throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
				}
			}
			if (ValidatorConst.FOREIGN_INDIVIDUAL.equals(type)) {
				if (!IdNumberUtil.isValidForeignIndividual((String) fieldValue)) {
					throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
				}
			}
			if (ValidatorConst.FOREIGN_LEGAL_PERSON.equals(type)) {
				if (!IdNumberUtil.isValidForeignLegalPerson((String) fieldValue)) {
					throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
				}
			}

		}

	}

	private void checkPattern(Validator annotation, Object fieldValue, String fieldName) {
		String pattern = annotation.pattern();
		if (StringUtils.isNotBlank(pattern)) {
			// 不等於字串
			if (!isValidString(fieldValue)) {
				throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
			}
			// 字串非空且不matchPattern拋錯
			if (StringUtils.isNotBlank((String) fieldValue) && !((String) fieldValue).matches(pattern)) {
				LOGGER.info("pattern is invalid");
				throw ReqValidatorException.createByErrCodeAndArgs(RtnCode.C0098, new String[] { fieldName });
			}
		}
	}

	// 參數不等於字串
	private boolean isValidString(Object fieldValue) {
		if (fieldValue instanceof String) {
			if (StringUtils.isNotBlank((String) fieldValue)) {
				return true;
			} else {
				LOGGER.error("String is Empty");
				return false;
			}
		}
		LOGGER.error("Value can't instance String");
		return false;
	}

	private String getClassName(String fieldName) {
		String[] classNameArray = fieldName.split("class");
		return classNameArray[1].trim();
	}

	private Method getFieldMethod(Field field, Map<String, Method> methodMap) {
		String fieldName = field.getName();
		String methodName = "get" + fieldName.toLowerCase();
		Method method = methodMap.get(methodName);
		return method;
	}

}
