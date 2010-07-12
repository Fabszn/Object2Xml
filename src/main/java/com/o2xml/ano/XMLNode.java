package com.o2xml.ano;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Documented
@Retention(RUNTIME)
public @interface XMLNode {
	
	
	String xpath() ;
	String name();

}
