package com.mantismonitor.webservice;

import java.net.URL;

import biz.futureware.mantisconnect.MantisConnect;
import biz.futureware.mantisconnect.MantisConnectLocator;
import biz.futureware.mantisconnect.MantisConnectPortType;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

import com.mantismonitor.configuration.MantisMonitorConfiguration;

@Component
@ApplicationScoped
public class MantisConnectPortTypeFactory implements ComponentFactory<MantisConnectPortType>{
	
	private MantisConnectPortType mantisConnect;
	private MantisMonitorConfiguration configuration;
	
	public MantisConnectPortTypeFactory(MantisMonitorConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public MantisConnectPortType getInstance() {
//		if (mantisConnect == null) {
//			try {
//				MantisConnect locator = new MantisConnectLocator();
//				mantisConnect = locator.getMantisConnectPort(new URL(configuration.getWebserviceEndpoint()));
//			} catch(Exception ex) {
//				throw new RuntimeException(ex);
//			}
//		}
		
		return mantisConnect;
	}
	
}
