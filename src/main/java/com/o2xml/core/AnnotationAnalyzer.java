package com.o2xml.core;

import static com.o2xml.core.EngineUtils.compileValue;
import static com.o2xml.core.EngineUtils.compileValues;
import static com.o2xml.core.EngineUtils.extractNodeNameFromMethodSignature;
import static com.o2xml.core.EngineUtils.isCollectionType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.o2xml.ano.XMLNode;
import com.o2xml.ano.XMLNodes;
import com.o2xml.ano.XMLNodesAdvanced;
import com.o2xml.core.EngineUtils;
import com.o2xml.core.NodeItem;
import com.o2xml.core.exception.EngineException;

public class AnnotationAnalyzer {

	public static List<NodeItem> analyze(Object o, Method m, XMLNode an)
			throws EngineException {
		Object returnObject = null;

		List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		try {
			returnObject = m.invoke(o, new Object[0]);
			// TODO : Factorisation du traitement des nodeItems
			if (EngineUtils.isArrayType(returnObject)) {
				String nodeParent = extractNodeNameFromMethodSignature(m, an);
				for (Object currentobject : (Object[]) returnObject) {
					nodeItems.addAll(NodeItem.buildNodeItem(compileValue(
							currentobject, an.method()), an, nodeParent));
				}
			} else if (isCollectionType(returnObject)) {
				String nodeParent = extractNodeNameFromMethodSignature(m, an);
				for (Object currentobject : (Collection<?>) returnObject) {
					nodeItems.addAll(NodeItem.buildNodeItem(compileValue(
							currentobject, an.method()), an, nodeParent));
				}
			} else {
				String nodeParent = (an).nodeParent();
				nodeItems.addAll(NodeItem.buildNodeItem(compileValue(
						returnObject, an.method()), an,
						(nodeParent.isEmpty() ? null : nodeParent)));
			}

			return nodeItems;

		} catch (Exception e) {
			throw new EngineException(
					"Exception throws during method theatment : " + m, e);
		}
	}

	public static List<NodeItem> analyze(Object o, Method m, XMLNodes an)
			throws EngineException {

		List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		try {
			Object returnObject = m.invoke(o, new Object[0]);

			String nodeParent = (an).nodeParent();
			nodeItems.addAll(NodeItem.buildNodeItem(compileValues(returnObject,
					an.methods()), an.names(), (nodeParent.isEmpty() ? null
					: nodeParent), an.isHideIfNull()));

			return nodeItems;

		} catch (Exception e) {
			throw new EngineException(
					"Exception throws during method theatment : " + m, e);
		}
	}

	public static List<NodeItem> analyze(Object o, Method m, XMLNodesAdvanced an)
			throws EngineException {

		List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		try {
			Object listObject = m.invoke(o, new Object[0]);

			if (listObject != null) {
				if (!EngineUtils.isCollectionType(listObject)) {
					throw new IllegalArgumentException(
							"it's a wrong type, it must be a sub-class  of collection");
				}

				for (Object co : (Collection<?>) listObject) {
					// Object returnObject = m.invoke(co, new Object[0]);

					String nodeParent = (an).nodeParent();
					nodeItems.addAll(NodeItem.buildNodeItem(compileValues(co,
							an.methods()), an.names(),
							(nodeParent.isEmpty() ? null : nodeParent), an
									.isHideIfNull()));

				}
			}
			return nodeItems;

		} catch (Exception e) {
			throw new EngineException(
					"Exception throws during method analyze : " + m, e);
		}
	}

}
