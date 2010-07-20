package com.o2xml.xml;

public interface XmlBuilder {
	
	public XmlElement findElementByXPathQuery(String xPathQuery);
	public XmlElement findElementById(String id);
	public String getXml();

}
