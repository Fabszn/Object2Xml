package com.o2xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

import com.o2xml.ano.XMLNode;
import com.o2xml.el.MethodInvocator;

/**
 * @author fsznajderman
 */
public class EngineUtils {

	private static final String ARRAY_REFLECT_DESC = "class [";
	private static final String ACCESSOR_STRING_KEY_GET = "get";

	protected static boolean isArrayType(Object returnType) {
		return (returnType.getClass().toString().startsWith(ARRAY_REFLECT_DESC));
	}

	protected static boolean isCollectionType(Object returnType) {

		return (returnType instanceof Collection);

	}

	protected static String extractNodeNameFromMethodSignature(Method _m,
			Annotation _an) {
		final String nodeParent = ((XMLNode) _an).nodeParent();
		String finalName = null;
		if (nodeParent.isEmpty()) {

			String name = _m.getName();

			if (name.startsWith(ACCESSOR_STRING_KEY_GET)) {
				finalName = name.substring(3, name.length());
			} else {
				finalName = name.substring(0, name.length());
			}
		} else {
			finalName = nodeParent;
		}
		return finalName;
	}
	
	public static String compileValue(Object o,Annotation _an){
		final String method = ((XMLNode) _an).method();
		String value = null;
		
		if(method.isEmpty()){
			value = o.toString();
		}else{
			try {
				value=MethodInvocator.invoke(o, method);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return value;
	}

}
