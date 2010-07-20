package com.o2xml.xml;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author fsznajderman
 *
 */

public class DOMXmlBuilder implements XmlBuilder {
	
	private Document internalDocument=null;
	private Element root = null;
	
	protected DOMXmlBuilder(String _root){
		if(_root==null){
			throw new NullPointerException("La racine ne peut etre null pour la creation");
		}
		System.out.println(_root);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructeur;
		try {
			constructeur = factory.newDocumentBuilder();
		
			internalDocument = constructeur.newDocument();

		} catch (ParserConfigurationException e) {
	
			e.printStackTrace();
		}

		internalDocument.setXmlVersion("1.0");
		internalDocument.setXmlStandalone(true);
		root = internalDocument.createElement(_root);
		internalDocument.appendChild(root);
	}
	
	/**
	 * search the corresponding Xpath element. 
	 * @param xPathQuery
	 * @return XmlElement
	 */
	public XmlElement findElementByXPathQuery(String xPathQuery){
		
		//implementation de la recherche base sur un element a partir d'une requete Xpath
		return null;
		
	}
	
	public XmlElement findElementById(String id){
		return new DOMXmlElement(internalDocument.getElementsByTagName(id).item(0),this.internalDocument);
	}

	@Override
	public String getXml() {
		StringWriter sw = null;
		
		try {
			Source source = new DOMSource(internalDocument);
			sw = new StringWriter();
			Result r = new StreamResult(sw);
			
			
			TransformerFactory fabrique = TransformerFactory.newInstance();
			Transformer transformer = fabrique.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			
			transformer.transform(source, r);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return sw.toString();
	}
	
	
	
	
	
	
	
	

}
