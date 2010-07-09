package com.o2xml.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class DOMXmlBuilder implements XmlBuilder {
	
	private Document internalDocument=null; 
	
	protected DOMXmlBuilder(String _root){
		if(_root==null){
			throw new NullPointerException("La racine ne peut etre null pour la creation");
		}
		
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
		internalDocument.createElement(_root);
			
	}
	
	/**
	 * search the corresponding Xpath element. 
	 * @param xPathQuery
	 * @return XmlElement
	 */
	public XmlElement findElement(String xPathQuery){
		
		//implementation de la recherche base sur un element a partir d'une requete Xpath
		return null;
		
	}
	
	
	

}
