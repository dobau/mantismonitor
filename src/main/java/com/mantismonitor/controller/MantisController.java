package com.mantismonitor.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.mantismonitor.entity.Mantis;
import com.mantismonitor.entity.User;
import com.mantismonitor.intercept.Json;
import com.mantismonitor.service.MantisService;
import com.mantismonitor.util.UserSession;

@Resource
@Path("/mantis")
public class MantisController {
	
	private Result result;
	private MantisService mantisService;
	private User user;
	
	public MantisController(MantisService mantisService, UserSession userSession, Result result) {
		this.result = result;
		this.mantisService = mantisService;
		this.user = userSession.getUser();
	}
	
	@Json
	@Path("/refresh/{mantisId}")
	public void refresh(Long mantisId) {
		Mantis mantis = mantisService.refreshAndGetMantis(user, mantisId);
		
		result.include("mantis", mantis);
	}

}
