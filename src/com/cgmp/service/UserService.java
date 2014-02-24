package com.cgmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.futureware.mantisconnect.MantisConnectPortType;

import com.cgmp.entity.User;

public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(MantisService.class);

	private MantisConnectPortType mantisConnect;
	private User user;
	private UserDao userDao;
	
	public UserService(MantisConnectPortType mantisConnect, UserDao userDao) {
		this.mantisConnect = mantisConnect;
		this.user = user;
		this.userDao = userDao;
	}
	
	public void login(String login, String password) {
		mantisConnect.mc_
	}

	
}
