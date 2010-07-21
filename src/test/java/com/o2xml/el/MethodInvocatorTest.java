package com.o2xml.el;

import org.junit.Assert;
import org.junit.Test;

import com.o2xml.core.CustomerBean;

public class MethodInvocatorTest {

	@Test
	public void methodInvocatorTest(){
		
		CustomerBean c = new CustomerBean();
		c.setName("nom");
		
		
		try {
			String valeur = MethodInvocator.invoke(c,"getName");
			Assert.assertEquals("nom", valeur);
			System.out.println(valeur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
