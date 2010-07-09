package com.o2xml.core;

import org.junit.Test;


public class Object2XmlEngineTest{


	@Test
	public void tranformObject2Xml(){
		
		CustomerBean c = new CustomerBean();
		Object2XmlEngine.transformObject2XML(c);
		
	}
	
	
	
	
	
	
}
