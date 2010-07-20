package com.o2xml.xml;

public interface XmlElement {
	
	
	public XmlElement addChild(String tagName, String value);
	public XmlElement addChild(String tagName, String tagValue, String tagAttributeName, String attributeValue);
	
	/**
	 * indicated if the encapsulated element is valid
	 * @return true if it's valid, otherwise false.
	 */
	public boolean isValidXmlElement();

}
