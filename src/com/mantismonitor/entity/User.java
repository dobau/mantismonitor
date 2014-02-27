package com.mantismonitor.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

	@Id
	private String login;
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAccess;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<GroupMantis> groupMantisList;

	public User() {}
	
	public User(String login) {
		this.login = login;
	}

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

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	
}
