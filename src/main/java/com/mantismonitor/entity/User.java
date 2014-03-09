package com.mantismonitor.entity;

import java.util.ArrayList;
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
	private List<MantisGroup> mantisGroupList;

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

	public List<MantisGroup> getMantisGroupList() {
		if (mantisGroupList == null) {
			mantisGroupList = new ArrayList<MantisGroup>();
		}
		
		return mantisGroupList;
	}

	public void setMantisGroupList(List<MantisGroup> mantisGroupList) {
		this.mantisGroupList = mantisGroupList;
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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		
		User aux = (User) obj;
		return getLogin() != null && getLogin().equals(aux.getLogin());
	}
	
}
