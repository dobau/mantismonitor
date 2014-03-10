package com.mantismonitor.configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class MantisMonitorConfiguration {

	public static final String WEBSERVICE_ENDPOINT_NAME = "mantisMonitor.webservice_endpoint";
	public static final String CACHE_TIME_NAME = "mantisMonitor.cache_time";

	public static final Integer CACHE_TIME_DEFAULT = 5; // 5 minutes

	private Integer cacheTime = null;
	private String webserviceEndpoint = null;

	public Integer getCacheTime() {
		if (cacheTime == null) {
			cacheTime = CACHE_TIME_DEFAULT;
		}

		return cacheTime;
	}

	public void setCacheTime(Integer cacheTime) {
		this.cacheTime = cacheTime;
	}

	public String getWebserviceEndpoint() {
		return webserviceEndpoint;
	}

	public void setWebserviceEndpoint(String webserviceEndpoint) {
		this.webserviceEndpoint = webserviceEndpoint;
	}

}
