package com.validator.demo.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.validator.demo.annotation.Validator;
import com.validator.demo.constant.AOPConst;
import com.validator.demo.constant.RtnCode;
import com.validator.demo.exception.ReqValidatorException;
import com.validator.demo.util.PojoUtil;
import com.validator.demo.validator.ReqValidator;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;



@Aspect
@Component
@Order(1)
public class ValidatorAspect<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);

	@Autowired
	ReqValidator reqValidator;

	@Before(value = AOPConst.POINTCUT_CONTROLLER)
	public void validateRequest(JoinPoint joinPoint) throws Exception {
		
		Object[] objs = joinPoint.getArgs();
		// 取出每一個參數名稱
		String [] parameterName = getParamName(joinPoint);
		
		int i = 0;
		for (Object obj : objs) {
			LOGGER.info(" i start : {} "+i);
		String requestClassName = getRequestClassName(joinPoint, parameterName[i]);
			//取出該obj 轉型的 ClassName
			Class clazz = Class.forName(requestClassName);
			if (clazz.isInstance(obj)) {
				Object objAsType = clazz.cast(obj);
				Map<String, Method> methodMap = PojoUtil.getMethodsOfObj(objAsType);
				Field[] declaredFields = objAsType.getClass().getDeclaredFields();
				//取出該obj 內有的方法
				for (Field field : declaredFields) {
					Method method = getFieldMethod(field, methodMap);
					// no getter don't validate
					if (method == null) {
						continue;
					}
					Validator annotation = PojoUtil.getAnnotation(field);
					if (isReqHeaderAndReqBodyparameterType(annotation)) {
						Object fieldValue = PojoUtil.invokeMethod(method, obj);		
						reqValidator.validate(fieldValue);
					}
				}
				i++;
			}

		}
	}
	
	private String[] getParamName(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String[] parameters = signature.getParameterNames();
		return parameters;
	}

	private boolean isReqHeaderAndReqBodyparameterType(Validator annotation) {
		return annotation != null
				&& (annotation.parameterType().equals("header") || annotation.parameterType().equals("body"));
	}

	private Method getFieldMethod(Field field, Map<String, Method> methodMap) {
		String fieldName = field.getName();
		String methodName = "get" + fieldName.toLowerCase();
		Method method = methodMap.get(methodName);
		return method;

	}

	private String getRequestClassName(JoinPoint joinPoint,String requestName) throws Exception {
		Map<String, Object> fieldsNamemap = getFieldsNameValueMap(joinPoint);
		String str = fieldsNamemap.get(requestName).toString();
		String requestClassName = str.split("@")[0];

		return requestClassName;
	}

	private Map<String, Object> getFieldsNameValueMap(JoinPoint joinPoint) throws Exception {
		Object[] args = joinPoint.getArgs();
		String classType = joinPoint.getTarget().getClass().getName();
		Class<?> clazz = Class.forName(classType);
		String clazzName = clazz.getName();
		String methodName = joinPoint.getSignature().getName(); // 获取方法名称
		Map<String, Object> map = new HashMap<String, Object>();
		ClassPool pool = ClassPool.getDefault();
		ClassClassPath classPath = new ClassClassPath(this.getClass());
		pool.insertClassPath(classPath);
		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			throw new RuntimeException();
		}
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			map.put(attr.variableName(i + pos), args[i]);// paramNames即参数
		}
		return map;
	}
}
