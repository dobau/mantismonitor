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
public class HomeController {
	
	private Result result;
	private UserSession userSession;
	private UserService userService;
	
	public HomeController(UserService userService, UserSession userSession, Result result) {
		this.userSession = userSession;
		this.result = result;
		this.userService = userService;
	}

	@Path("/")
	public void index() {
		if (userSession.islogged()) {
			result.forwardTo(MantisGroupController.class).getAll();
		}
	}

	@Post("/login")
	public void login(String login, String password) throws RemoteException {
		User user = userService.login(login, password);
		if (user == null) {
			result.include("error", "Failed to login");
		} else {
			userSession.setUser(user);
			result.include("success", "Login success");
		}
		
		result.redirectTo(this).index();
	}
	
	@Path("/logout")
	public void logout() {
		userSession.logout();
		result.include("notice" , "User logout");
		
		result.redirectTo(this).index();
	}
	
}
