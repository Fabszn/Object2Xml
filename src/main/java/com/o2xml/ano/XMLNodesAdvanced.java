package com.o2xml.ano;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface XMLNodesAdvanced {
	
	String[] names();
	String nodeParent() default "";
	String[] methods();
	boolean isHideIfNull() default false;
	boolean isStandaloneChildNode() default false;

}
