package com.o2xml.core;

import java.util.List;

public interface INodeItem {

	public  String getValue();

	public  String getNodeName();

	public  String getNodeParent();

	public  boolean isStandaloneNode();
	
	public List<NodeItem> getChild();

}