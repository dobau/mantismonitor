package com.mantismonitor.webservice;

import java.net.URL;
import java.util.Properties;

import biz.futureware.mantisconnect.MantisConnect;
import biz.futureware.mantisconnect.MantisConnectLocator;
import biz.futureware.mantisconnect.MantisConnectPortType;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class MantisConnectPortTypeFactory implements ComponentFactory<MantisConnectPortType>{
	
	private MantisConnectPortType mantisConnect;

	@Override
	public MantisConnectPortType getInstance() {
		if (mantisConnect == null) {
			try {
				Properties configProperties = new Properties();
				configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
				
				MantisConnect locator = new MantisConnectLocator();
				mantisConnect = locator.getMantisConnectPort(new URL(configProperties.getProperty("mantisconnect.endpoint")));
			} catch(Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
		
		return mantisConnect;
	}
	
}
