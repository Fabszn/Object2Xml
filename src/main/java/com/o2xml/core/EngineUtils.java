package com.o2xml.core;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author fsznajderman
 */
public class EngineUtils {
	
	private static final String ARRAY_REFLECT_DESC = "class [";
	private static final String ACCESSOR_STRING_KEY_GET = "get";

	protected static boolean isArrayType(Object returnType){
		return (returnType.getClass().toString().startsWith(ARRAY_REFLECT_DESC));
	}
	
	protected static boolean isCollectionType(Object returnType){

		return  (returnType instanceof Collection);

	}
	
	protected static String extractNodeNameFromMethodSignature(Method m){
		
		System.out.println(m.getName());
		String name = m.getName();
		String finalName = null;
		if(name.startsWith(ACCESSOR_STRING_KEY_GET)){
			finalName = name.substring(3, name.length());
		}else{
			finalName = name.substring(0, name.length());
		}
			
		return finalName;
	}
	
}
