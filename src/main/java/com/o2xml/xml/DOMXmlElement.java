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
	 * add child with his value to the current element.
	 * @return current XmlElement 
	 */
	@Override
	public XmlElement addChild(String tagName, String value) {
		Element elem = this.document.createElement(tagName);
		elem.setTextContent(value);
		element.appendChild(elem);
		
		return new DOMXmlElement(elem,this.document);

	}
	
	@Override
	public boolean isValidXmlElement() {
	
		return element!=null && document!=null;
	}


	@Override
	public String getId() {
		return element!=null?element.getNodeName():null;
	}
	
	@Override
	public XmlElement getParent() {
		
		return new DOMXmlElement(element.getParentNode(),document);
	}
	
	

	

	
	
	
	
}
