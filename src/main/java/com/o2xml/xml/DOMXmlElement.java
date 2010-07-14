package com.o2xml.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMXmlElement implements XmlElement{

	private Node element = null;
	private Document document = null;
	
	protected DOMXmlElement(Node _element,Document _refDocument){
		this.element =_element;	
		this.document = _refDocument;
	}


	/**
	 * add child whith an attribute and his value
	 */
	@Override
	public XmlElement addChild(String tagName, String tagValue,
			String tagAttributeName, String attributeValue) {
		
		throw new UnsupportedOperationException();
	//	return null;
	}

	/**
	 * add child to the current element.
	 * @return current XmlElement 
	 */
	@Override
	public XmlElement addChild(String tagName, String value) {
		Element nom = this.document.createElement(tagName);
		nom.setTextContent(value);
		element.appendChild(nom);
		
		return this;

	}

	

	
	
	
	
}
