package com.cgmp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String login;
	private String password;
	private List<GroupMantis> groupMantisList;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<GroupMantis> getGroupMantisList() {
		return groupMantisList;
	}

	public void setGroupMantisList(List<GroupMantis> groupMantisList) {
		this.groupMantisList = groupMantisList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
