package com.mantismonitor.message;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Type {
		SUCCESS, ERROR, WARNING
	}

	private String message;
	private Type type;

	public Message(Type type, String message) {
		this.type = type;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
