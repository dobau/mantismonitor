package com.mantismonitor.service;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biz.futureware.mantisconnect.MantisConnectPortType;
import br.com.caelum.vraptor.ioc.Component;

import com.mantismonitor.entity.Mantis;
import com.mantismonitor.entity.User;
import com.mantismonitor.repository.MantisRepository;

@Component
public class MantisService {
	
	private static final Logger logger = LoggerFactory.getLogger(MantisService.class);

	private MantisConnectPortType mantisConnect;
	private MantisRepository mantisRepository;
	
	public MantisService(MantisConnectPortType mantisConnect, MantisRepository mantisRepository) {
		this.mantisConnect = mantisConnect;
		this.mantisRepository = mantisRepository;
	}
	
	public Mantis refreshAndGetMantis(User user, Long id) {
		Mantis mantis = mantisRepository.findOne(id);
		
		// 1 minute
		if (mantis.getLastRefresh() == null || (System.currentTimeMillis() - mantis.getLastRefresh().getTime()) > 1*60*1000) {
			
			/*
		try {
			IssueData issueData = mantisConnect.mc_issue_get(user.getLogin(), user.getPassword(), new BigInteger(mantis.getId().toString()));

			mantis.setLastRefresh(new Date());
			mantis.setName(issueData.getDescription());
			mantis.setStatus(issueData.getStatus().getName());
			mantis.setUserReporter(issueData.getReporter().getName());
			mantisRepository.save(mantis);
		} catch (RemoteException e) {
			logger.warn("Erro ao recuperar issue {}", e.getMessage());
		}
			 */
			
			// TODO Remove test code
			mantis.setLastRefresh(new Date());
			mantis.setName(UUID.randomUUID().toString());
			mantis.setStatus(UUID.randomUUID().toString());
			mantis.setUserReporter(UUID.randomUUID().toString());
			
			mantisRepository.save(mantis);
		}
		
		return mantis;
	}
	
	public Mantis add(Long id) {
		Mantis mantis = mantisRepository.findOne(id);
		
		if (mantis == null) {
			mantis = mantisRepository.save(new Mantis(id));
		}
		
		return mantis;
	}
	
}
