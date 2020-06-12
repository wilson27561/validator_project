package com.validator.demo.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.validator.demo.annotation.Validator;

public class PojoUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PojoUtil.class);
	
	public static Validator getAnnotation(Field object) {
			Validator result = object.getAnnotation(Validator.class);
			return result;
		
	}

	public static Map<String, Method> getMethodsOfObj(Object inputObj) {

		Map<String, Method> methodMap = new HashMap<>();
		MethodDescriptor[] mathodDescriptors = new MethodDescriptor[0];

		try {
			mathodDescriptors = Introspector.getBeanInfo(inputObj.getClass()).getMethodDescriptors();
		} catch (IntrospectionException e) {
			LOGGER.debug(e.getMessage(), e);
		}
		for (MethodDescriptor methodDescriptor : mathodDescriptors) {
			Method m = methodDescriptor.getMethod();
			methodMap.put(m.getName().toLowerCase(), m);
		}

		return methodMap;
	}

	public static Object invokeMethod(Method method, Object inputObj) {

		try {
			return method.invoke(inputObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
