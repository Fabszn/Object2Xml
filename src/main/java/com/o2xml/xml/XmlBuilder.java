package com.o2xml.xml;

public interface XmlBuilder {
	
	public XmlElement findElement(String xPathQuery);
	public XmlElement findElementById(String id);
	
	public String getXml();

}
