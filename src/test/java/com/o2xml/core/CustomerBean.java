package com.o2xml.core;

import java.util.List;

import com.o2xml.ano.XMLNode;
import com.o2xml.ano.XMLRoot;

@XMLRoot(rootName="RacineDelobjet")
public class CustomerBean {
    private String name = null;
	private Order[] orders = null;
	private List<Order> o = null;
	private String[] emailAdresses = null;

	@XMLNode(name="MyName")
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	@XMLNode(name="order")
	public Order[] getOrders() {
		return orders;
	}
	
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}
	
	
	public String[] getEmailAdresses() {
		return emailAdresses;
	}

	public void setEmailAdresses(String[] emailAdresses) {
		this.emailAdresses = emailAdresses;
	}
	
	public double[] getAll(){
		return new double[]{12};
	}
	
	public Double[] getAllDoubles(){
		return new Double[]{new Double(12)};
	}
	
	@XMLNode(name="odr")
	public List<Order> getCollection(){
		return o;
	}
	
	public void setCollection(List<Order> o){
		this.o=o;
	}
	
}