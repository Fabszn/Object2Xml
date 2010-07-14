package com.o2xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.o2xml.XmlTagUtils;
import com.o2xml.ano.XMLNode;
import com.o2xml.ano.XMLRoot;

public class Object2XmlEngine {
	
	
	
	
	public static void transformObject2XML(Object o){
				
		Method[] m =  o.getClass().getMethods();
		for (Method me : m) {
		
			Annotation[] a = me.getAnnotations();
			
			for(Annotation an : a){
				System.out.println(an);
			}
		
		}	
		
		
	}
	
	
	
	private String getRootDocument(Object o){
		XMLRoot xr = o.getClass().getAnnotation(XMLRoot.class);
		String racineValue = null;
		if(xr != null){
			
			racineValue = xr.rootName(); 
			
		}else{
			racineValue = o.getClass().getSimpleName();
		}
		
		return racineValue;
	}

}
