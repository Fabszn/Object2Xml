package com.o2xml.core;

import static com.o2xml.core.AnnotationAnalyzer.analyze;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.o2xml.ano.XMLNode;
import com.o2xml.ano.XMLNodes;
import com.o2xml.ano.XMLNodesAdvanced;
import com.o2xml.ano.XMLRoot;
import com.o2xml.core.exception.EngineException;
import com.o2xml.xml.XmlBuilder;
import com.o2xml.xml.XmlBuilderFactory;
import com.o2xml.xml.XmlElement;


public class Object2XmlEngine {

	public static void transformObject2XML(Object o) throws EngineException {

		final String root = getRootDocument(o);
		final List<NodeItem> l = getNodeInformations(o);

		XmlBuilder dxb = XmlBuilderFactory.createXmlBuilder(root);

		for (NodeItem ni : l) {
			XmlElement parent = obtainParentNode(root, dxb, ni);
			parent.addChild(ni.getNodeName(), ni.getValue());

		}

		System.out.println(dxb.getXml());
	}

	private static List<NodeItem> getNodeInformations(Object o)
			throws EngineException {
		List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		try {
			Method[] m = o.getClass().getMethods();
			for (Method me : m) {

				XMLNode xmlNode = getAnnotationInformationFromMethod(me,
						XMLNode.class);
				if (xmlNode != null) {

					nodeItems.addAll(analyze(o, me, xmlNode));

				}
				XMLNodes xmlNodes = getAnnotationInformationFromMethod(me,
						XMLNodes.class);
				if (xmlNodes != null) {
					nodeItems.addAll(analyze(o, me, xmlNodes));
				}

				XMLNodesAdvanced xmlNodesAdvanced = getAnnotationInformationFromMethod(
						me, XMLNodesAdvanced.class);
				if (xmlNodesAdvanced != null) {
					nodeItems.addAll(analyze(o, me, xmlNodesAdvanced));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nodeItems;
	}

	private static String getRootDocument(Object o) {
		XMLRoot xr = o.getClass().getAnnotation(XMLRoot.class);
		String racineValue = null;
		if (xr != null) {
			racineValue = xr.rootName();
		} else {
			racineValue = o.getClass().getSimpleName();
		}

		return racineValue;
	}

	private static <T> T getAnnotationInformationFromMethod(Method m,
			Class<T> clazz) {

		Annotation[] a = m.getAnnotations();
		for (Annotation an : a) {
			if (an.annotationType().equals(clazz)) {
				return (T) an;
			}
		}

		return null;

	}

	private static XmlElement obtainParentNode(final String root,
			XmlBuilder dxb, NodeItem ni) {

		
		String n = ni.getParentNode();
		XmlElement refNode = null;
		XmlElement refParent = dxb.findElementById(root);
		if (n != null) {

			final StringTokenizer st = new StringTokenizer(n, "#");
			
			while (st.hasMoreTokens()) {
				String node = st.nextToken();
			
				refNode = dxb.findElementById(node);
				if (!refNode.isValidXmlElement()) {
					refParent = dxb.findElementById(refParent.getId())
							.addChild(node, null);
				}else{
					refParent=refNode;
				}

			}

		}
		return refParent;
	}

}

class NodeItem {

	private String nodeName = null;
	private String value = null;
	private String parentNode = null;

	private NodeItem(String _nodeName, String _value) {
		this.nodeName = _nodeName;
		this.value = _value;
	}

	private NodeItem(String _nodeName, String _value, String _nodeParent) {
		this.nodeName = _nodeName;
		this.value = _value;
		this.parentNode = _nodeParent;
	}

	protected static List<NodeItem> buildNodeItem(List<String> values,
			XMLNode an, String nodeParent) {
		final List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		for (String value : values) {
			if ((!value.isEmpty()) || (value.isEmpty() && !an.isHideIfNull())) {
				nodeItems.add(new NodeItem((an).name(), value, nodeParent));
			}
		}

		return nodeItems;
	}

	protected static List<NodeItem> buildNodeItem(List<String> values,
			String[] names, String nodeParent, boolean isHideIfNull) {
		final List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		for (int i = 0; i < values.size(); i++) {

			String value = values.get(i);
			if ((!value.isEmpty()) || (value.isEmpty() && !isHideIfNull)) {
				nodeItems.add(new NodeItem(names[i], value, nodeParent));
			}
		}

		return nodeItems;

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

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

}
