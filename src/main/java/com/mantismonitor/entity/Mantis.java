package com.mantismonitor.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mantis {

	@Id
	private Long id;
	private String name;

	private String status;
	private Date lastRefresh;
	private String userReporter;
	private String userResponsable;

	public Mantis() {}
	
	public Mantis(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastRefresh() {
		return lastRefresh;
	}

	public void setLastRefresh(Date lastRefresh) {
		this.lastRefresh = lastRefresh;
	}

	public String getUserReporter() {
		return userReporter;
	}

	public void setUserReporter(String userReporter) {
		this.userReporter = userReporter;
	}

	public String getUserResponsable() {
		return userResponsable;
	}

	public void setUserResponsable(String userResponsable) {
		this.userResponsable = userResponsable;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Mantis) || obj == null || getId() == null) {
			return false;
		}
		
		Mantis aux = (Mantis) obj;
		return getId().equals(aux.getId());
	}
	
}
