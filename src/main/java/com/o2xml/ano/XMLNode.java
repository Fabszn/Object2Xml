package com.o2xml.ano;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface XMLNode {
	
	
	String xpath() default "" ;
	String name();

}
