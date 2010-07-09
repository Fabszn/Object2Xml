package com.o2xml.xml;

public interface XmlElement {
	
	
	public XmlElement addChild(String tagName, String value);
	public XmlElement addChild(String tagName, String tagValue, String tagAttributeName, String attributeValue);

}
