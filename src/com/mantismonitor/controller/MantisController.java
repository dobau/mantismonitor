package com.mantismonitor.controller;

import java.rmi.RemoteException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.mantismonitor.entity.User;
import com.mantismonitor.service.UserService;
import com.mantismonitor.util.UserSession;

@Resource
public class MantisController {
	
	private Result result;
	private UserSession userSession;
	private UserService userService;
	
	public MantisController(UserService userService, UserSession userSession, Result result) {
		this.userSession = userSession;
		this.result = result;
		this.userService = userService;
	}

	@Path("/")
	public void index() {
		
	}

	@Post
	public void login(String login, String password) throws RemoteException {
		User user = userService.login(login, password);
		if (user == null) {
			result.include("error", "Failed to login");
		} else {
			userSession.setUser(user);
			result.include("success", "Login success");
		}
		
		result.forwardTo(this).index();
	}
	
	public void logout() {
		
	}
	
}
