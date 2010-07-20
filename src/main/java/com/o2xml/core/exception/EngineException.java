package com.o2xml.core.exception;

/**
 * @author fsznajderman
 */
@SuppressWarnings("serial")
public class EngineException extends Exception {

	
	public EngineException(String msg, Throwable e) {
		super(msg,e);
	}
	
	public EngineException(String msg) {
		super(msg);
	}
}
