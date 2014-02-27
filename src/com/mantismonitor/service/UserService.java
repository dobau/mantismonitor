package com.mantismonitor.service;

import java.rmi.RemoteException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.futureware.mantisconnect.MantisConnectPortType;
import biz.futureware.mantisconnect.ObjectRef;
import br.com.caelum.vraptor.ioc.Component;

import com.mantismonitor.entity.User;
import com.mantismonitor.repository.UserRepository;
import com.mantismonitor.util.UserSession;

@Component
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(MantisService.class);

	private MantisConnectPortType mantisConnect;
	private UserRepository userDao;
	
	public UserService(MantisConnectPortType mantisConnect, UserRepository userDao) {
		this.mantisConnect = mantisConnect;
		this.userDao = userDao;
	}
	
	public User login(String login, String password) throws RemoteException {
		ObjectRef[] result = mantisConnect.mc_enum_access_levels(login, password);
		if (result != null) {
			User user = userDao.findByLogin(login);
			if (user == null) {
				user = new User(login);
			}
			
			user.setLastAccess(new Date());
			
			return userDao.save(user);
		}
		
		return null;
	}

	
}
