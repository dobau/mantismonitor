package com.mantismonitor.util;

public class SuccessMessage extends Message {
	
	private static final long serialVersionUID = 1L;

	public SuccessMessage(String message) {
		super(Type.SUCCESS, message);
	}
	
}
