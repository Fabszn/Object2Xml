package com.o2xml.core;

import java.util.ArrayList;
import java.util.List;

public class NodeItem implements INodeItem {

	private String nodeName = null;
	private String value = null;
	private String parentNode = null;
	private List<NodeItem> child = null;
	private boolean isStandaloneNode = false;

	
	

	private NodeItem(String _nodeName, boolean _isStandalone) {
		this.nodeName = _nodeName;
		this.isStandaloneNode = _isStandalone;
	}

	private NodeItem(String _nodeName, String _value, String _nodeParent) {
		this.nodeName = _nodeName;
		this.value = _value;
		this.parentNode = _nodeParent;
	}

	public static List<NodeItem> buildNodeItem(List<String> values,
			String name, String nodeParent,boolean isHideIfNull) {
		final List<NodeItem> nodeItems = new ArrayList<NodeItem>();

		for (String value : values) {
			if ((!value.isEmpty()) || (value.isEmpty() && !isHideIfNull)) {
				nodeItems.add(new NodeItem(name, value, nodeParent));
			}
		}

		return nodeItems;
	}

	public static List<NodeItem> buildNodeItem(List<String> values,
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
	
	public static NodeItem buildNodeItem(String nodeName,String nodeParent, boolean isStandalone) {
		
		NodeItem n = new NodeItem(nodeName,isStandalone);
		n.setNodeParent(nodeParent);
		return n;

	}

	/* (non-Javadoc)
	 * @see com.o2xml.core.INodeItem#getValue()
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.o2xml.core.INodeItem#getNodeName()
	 */
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/* (non-Javadoc)
	 * @see com.o2xml.core.INodeItem#getParentNode()
	 */
	public String getNodeParent() {
		return parentNode;
	}

	public void setNodeParent(String parentNode) {
		this.parentNode = parentNode;
	}

	public List<NodeItem> getChild() {
		return child;
	}

	public void setChild(List<NodeItem> child) {
		this.child = child;
	}

	/* (non-Javadoc)
	 * @see com.o2xml.core.INodeItem#isStandaloneNode()
	 */
	public boolean isStandaloneNode() {
		return isStandaloneNode;
	}

	public void setStandaloneNode(boolean isStandaloneNode) {
		this.isStandaloneNode = isStandaloneNode;
	}
	
}
