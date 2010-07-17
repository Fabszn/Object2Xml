package com.o2xml.core;

import java.util.Collection;

/**
 * @author fsznajderman
 */
public class EngineUtils {
	
	private static final String ARRAY_REFLECT_DESC = "class [";

	protected static boolean isArrayType(Object returnType){
		return (returnType.getClass().toString().startsWith(ARRAY_REFLECT_DESC));
	}
	
	protected static boolean isCollectionType(Object returnType){

		return  (returnType instanceof Collection);

	}
	
}
