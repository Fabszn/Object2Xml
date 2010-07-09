package com.o2xml.core;

import com.o2xml.ano.XMLNode;

public class CustomerBean {
    private String name = null;
	private Order[] orders = null;
	
	private String[] emailAdresses = null;

	@XMLNode(path="te",name="dsg")
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

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
	
}