package com.o2xml.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.o2xml.core.exception.EngineException;


public class Object2XmlEngineTest{


	@Test
	public void tranformObject2Xml(){
		
		CustomerBean c = new CustomerBean();
		c.setName("Le nom du capitaine est biniou");
		buildArray(c);
		buildCollection(c);
		
		try {
			Object2XmlEngine.transformObject2XML(c);
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void buildArray(CustomerBean c) {
		Order[] t = new Order[2];
		Order o = new Order();
		o.setId(1);
		t[0]=o;
		Order o1 = new Order();
		o1.setId(2);
		t[1]=o1;
		c.setOrders(t);
	}
	
	private void buildCollection(CustomerBean c) {
		List<Order> orders = new ArrayList<Order>();
		Order o = new Order();
		o.setId(1);
		orders.add(o);
		Order o1 = new Order();
		o1.setId(2);
		orders.add(o1);
		c.setCollection(orders);
		
	}
	
	
	
	
	
}
