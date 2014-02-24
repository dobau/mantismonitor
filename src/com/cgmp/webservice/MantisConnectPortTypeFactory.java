package com.cgmp.webservice;

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
				MantisConnect locator = new MantisConnectLocator();
				mantisConnect = locator.getMantisConnectPort();
			} catch(Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		
		return mantisConnect;
	}
	
}
