package com.o2xml.el;

import java.lang.reflect.Method;

public class MethodInvocator {
	
	/**
	 * give object instance and method name to invoke on it
	 * @param o
	 * @param methodName
	 * @return value returned of specified method by methodName 
	 */
	public static String invoke(Object o, String methodName) throws Exception{
		
		String valeur = null;
		try {
			Method m = o.getClass().getMethod(methodName, null);

			valeur = ""+m.invoke(o,new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return valeur;
		
	}
	
	

}
