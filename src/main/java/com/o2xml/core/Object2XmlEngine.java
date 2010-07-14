package com.o2xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.o2xml.ano.XMLNode;
import com.o2xml.ano.XMLRoot;
import com.o2xml.xml.XmlBuilder;
import com.o2xml.xml.XmlBuilderFactory;

public class Object2XmlEngine {




	public  static  void transformObject2XML(Object o){


		final String root = getRootDocument(o);
		final List<NodeItem> l = getNodeInformations(o);

		XmlBuilder dxb = XmlBuilderFactory.createXmlBuilder(root);
		for(NodeItem ni : l){
			dxb.findElementById(root).addChild(ni.getNodeName(), ni.getValue());
		}

		System.out.println(dxb.getXml());
	}



	private static List<NodeItem> getNodeInformations(Object o) {
		List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		try{
			Method[] m =  o.getClass().getMethods();
			for (Method me : m) {
				Annotation[] a = me.getAnnotations();

				for(Annotation an : a){
					if(an.annotationType().equals(XMLNode.class)){
						NodeItem nodeItem = new NodeItem(((XMLNode)an).name(),((XMLNode)an).xpath(),""+me.invoke(o,new Object[0]));
						nodeItems.add(nodeItem);
					}

				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return nodeItems;
	}



	private static String getRootDocument(Object o){
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


class NodeItem{


	private String nodeName = null;
	private String xpathDef = null;
	private String value = null;




	public NodeItem(String _nodeName,String _xpathDef, String _value) {
		this.nodeName=_nodeName;
		this.xpathDef=_xpathDef;
		this.value=_value;
	}

	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}


	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getXpathDef() {
		return xpathDef;
	}
	public void setXpathDef(String xpathDef) {
		this.xpathDef = xpathDef;
	}






}
