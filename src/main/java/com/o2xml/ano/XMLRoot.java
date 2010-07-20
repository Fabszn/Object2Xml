package com.o2xml.ano;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;


@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface XMLRoot {
	
	String rootName();

}
