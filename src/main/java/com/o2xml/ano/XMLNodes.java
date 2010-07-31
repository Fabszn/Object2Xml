package com.o2xml.ano;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface XMLNodes  {
	
	
	String xpath() default "" ;
	String[] names();
	String nodeParent() default "";
	String[] methods();
	boolean isHideIfNull() default false;

}
