package com.mantismonitor.util;

public class WarningMessage extends Message {
	
	private static final long serialVersionUID = 1L;

	public WarningMessage(String message) {
		super(Type.WARNING, message);
	}
	
}
