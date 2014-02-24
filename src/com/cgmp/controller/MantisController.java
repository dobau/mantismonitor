package com.cgmp.controller;

import br.com.caelum.vraptor.Resource;

import com.cgmp.entity.User;
import com.cgmp.service.UserService;

@Resource
public class MantisController {
	
	private User user;
	
	public MantisController(UserService userService, User user) {
		
	}

	public void index() {
		
	}

	public void login(String login, String password) {
		
	}
	
	public void logout() {
		
	}
	
}
