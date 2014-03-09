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

@Component
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(MantisService.class);

	private MantisConnectPortType mantisConnect;
	private UserRepository userRepository;
	
	public UserService(MantisConnectPortType mantisConnect, UserRepository userRepository) {
		this.mantisConnect = mantisConnect;
		this.userRepository = userRepository;
	}
	
	public User login(String login, String password) throws RemoteException {
		//ObjectRef[] result = mantisConnect.mc_enum_access_levels(login, password);
		ObjectRef[] result = new ObjectRef[1];
		if (result != null) {
			User user = userRepository.findByLogin(login);
			if (user == null) {
				user = new User(login);
			}
			
			user.setLastAccess(new Date());
			
			return userRepository.save(user);
		}
		
		return null;
	}

	
}
