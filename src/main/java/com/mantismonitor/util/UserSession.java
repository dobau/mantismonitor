package com.mantismonitor.util;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.mantismonitor.entity.User;

@Component
@SessionScoped
public class UserSession implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private User user;

	public boolean islogged() {
		return user != null;
	}

	public void logout() {
		user = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
