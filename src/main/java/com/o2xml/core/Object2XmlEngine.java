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

			if (ni.isStandaloneNode()) {
				XmlElement parent = obtainParentNode(root, dxb, ni);
				List<NodeItem> children = ni.getChild();
				for (INodeItem child : children) {
					parent.addChild(child.getNodeName(), child.getValue());
				}
			} else {
				XmlElement parent = obtainParentNode(root, dxb, ni);
				parent.addChild(ni.getNodeName(), ni.getValue());
			}

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
			XmlBuilder dxb, INodeItem ni) {

		String n = ni.getNodeParent();
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
				} else {
					refParent = refNode;
				}

			}
			
		}
		
		if (ni.isStandaloneNode()) {
			refParent = dxb.findElementById(refParent.getParent().getId()).addChild(refParent.getId(),
					null);
		}
		return refParent;
	}

}
