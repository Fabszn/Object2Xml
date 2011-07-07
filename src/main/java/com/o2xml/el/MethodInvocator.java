package com.o2xml.el;

import java.lang.reflect.Method;
import java.util.StringTokenizer;



public class MethodInvocator {

	private static final String KEY_METHOD_SEPARATOR = "#";

	/**
	 * give object instance and method name to invoke on it
	 * 
	 * @param o
	 * @param methodName
	 * @return value returned of specified method by methodName
	 */
	public static String invoke(Object o, String methodName) throws Exception {

		Object currentObject = o;
		String valeur = "";
		StringTokenizer st = new StringTokenizer(methodName, KEY_METHOD_SEPARATOR);
		int nbtoken = st.countTokens();
		int idx = 0;
		try {

			while (st.hasMoreTokens()) {
				if (currentObject != null) {
					Method m = currentObject.getClass().getMethod(
							st.nextToken(), null);

					currentObject = (m.invoke(currentObject, new Object[0]));

					if (++idx == nbtoken) {
						valeur = currentObject == null ? "" : ""
								+ currentObject;
					}
				}else{
					break;
				}
				
			}
		} catch (Exception e) {
			// TODO implemented specific exception
			e.printStackTrace();
		}

		return valeur;
	}

}
