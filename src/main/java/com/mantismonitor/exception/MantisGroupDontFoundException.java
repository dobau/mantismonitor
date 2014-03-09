package com.mantismonitor.exception;

public class MantisGroupDontFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MantisGroupDontFoundException(String msg) {
		super(msg);
	}
	
	public MantisGroupDontFoundException() {
		super("Mantis Group dont found ");
	}

}
