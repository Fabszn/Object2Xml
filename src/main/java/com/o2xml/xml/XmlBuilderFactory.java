package com.o2xml.xml;

public class XmlBuilderFactory {
	
	private XmlBuilderFactory(){}
	
	public static XmlBuilder createXmlBuilder(String root){
		
		return new DOMXmlBuilder(root);
		
	}

}
