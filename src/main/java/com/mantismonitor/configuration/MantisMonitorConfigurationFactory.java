package com.mantismonitor.configuration;

import java.io.IOException;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class MantisMonitorConfigurationFactory implements ComponentFactory<MantisMonitorConfiguration>{
	
	private MantisMonitorConfiguration mantisMonitorConfiguration;

	@Override
	public MantisMonitorConfiguration getInstance() {
		if (mantisMonitorConfiguration == null) {
			Properties configProperties = new Properties();
			try {
				configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			} catch (IOException e) {
				throw new ConfigurationFileNotFoundException();
			}
			
			mantisMonitorConfiguration = new MantisMonitorConfiguration();
			mantisMonitorConfiguration.setWebserviceEndpoint(configProperties.getProperty(MantisMonitorConfiguration.WEBSERVICE_ENDPOINT_NAME));
			mantisMonitorConfiguration.setCacheTime(new Integer(configProperties.getProperty(MantisMonitorConfiguration.CACHE_TIME_NAME)));
		}
		
		return mantisMonitorConfiguration;
	}

}
