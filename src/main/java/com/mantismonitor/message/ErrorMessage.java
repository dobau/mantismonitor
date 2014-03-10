package com.mantismonitor.message;

public class ErrorMessage extends Message {
	
	private static final long serialVersionUID = 1L;

	public ErrorMessage(String message) {
		super(Type.ERROR, message);
	}
	
}
