package com.cgmp.service;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.futureware.mantisconnect.IssueData;
import biz.futureware.mantisconnect.MantisConnectPortType;
import br.com.caelum.vraptor.ioc.Component;

import com.cgmp.dao.MantisDao;
import com.cgmp.entity.Mantis;
import com.cgmp.entity.User;

@Component
public class MantisService {
	
	private static final Logger logger = LoggerFactory.getLogger(MantisService.class);

	private MantisConnectPortType mantisConnect;
	private User user;
	private MantisDao mantisDao;
	
	public MantisService(MantisConnectPortType mantisConnect, User user, MantisDao mantisDao) {
		this.mantisConnect = mantisConnect;
		this.user = user;
		this.mantisDao = mantisDao;
	}
	
	public List<Mantis> getMantisByGroup(String group) {
		String login = user.getLogin();
		String password = user.getPassword();
		
		List<Mantis> mantisList = mantisDao.findMantisByGroup(login, group);
		
		for (Mantis mantis : mantisList) {
			try {
				IssueData issueData = mantisConnect.mc_issue_get(login, password, new BigInteger(mantis.getId().toString()));
				
				mantis.setLastRefresh(new Date());
				mantis.setName(issueData.getDescription());
				mantis.setStatus(issueData.getStatus().getName());
				mantis.setUserReporter(issueData.getReporter().getName());
				
				mantisDao.save(mantis);
			} catch (RemoteException e) {
				logger.warn("Erro ao recuperar issue {}", e.getMessage());
			}
		}
		
		return mantisList;
	}
	
}
