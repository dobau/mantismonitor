package com.mantismonitor.configuration;

public class ConfigurationFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConfigurationFileNotFoundException(){
		super("Configuration file config.properties does not found in classpath");
	}

}
