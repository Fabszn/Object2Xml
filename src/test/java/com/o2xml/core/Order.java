package com.o2xml.core;

public class Order {
	
	private int id = 0;

	public int getId() {
		return id;
	}

	public String orderLabel(){
		return "label " + id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
	
		return ""+id;
	}

} 
