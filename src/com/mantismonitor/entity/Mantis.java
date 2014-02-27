package com.mantismonitor.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mantis {

	@Id
	private Long id;
	private String name;

	private String status;
	private Date lastRefresh;
	private String userReporter;
	private String userResponsable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private GroupMantis groupMantis;

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

	public GroupMantis getGroupMantis() {
		return groupMantis;
	}

	public void setGroupMantis(GroupMantis groupMantis) {
		this.groupMantis = groupMantis;
	}

}
