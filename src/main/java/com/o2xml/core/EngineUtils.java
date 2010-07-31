package com.o2xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.o2xml.ano.XMLNode;
import com.o2xml.ano.XMLNodes;
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

	public static List<String> compileValue(Object o, XMLNode _an) {
		final String method = _an.method();
		
		List<String> values = new ArrayList<String>();
		try {
			if (!method.isEmpty()) {
				values.add(MethodInvocator.invoke(o, method));
			} else {
				values.add(o==null?"":o.toString());
			}
		} catch (Exception e) {
			//TODO implemented specific exception
			e.printStackTrace();
		}

		return values;
	}

	public static List<String> compileValues(Object o, XMLNodes _an) {
		final String[] methods = _an.methods();

		List<String> values = new ArrayList<String>();
		try {
			if (!(methods.length == 0)) {

				for (String m : methods) {
					values.add(MethodInvocator.invoke(o, m));
				}
			}
		} catch (Exception e) {
			//TODO implemented specific exception
			e.printStackTrace();
		}

		return values;
	}
}
