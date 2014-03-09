package com.mantismonitor.service;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

import com.mantismonitor.entity.Mantis;
import com.mantismonitor.entity.MantisGroup;
import com.mantismonitor.entity.User;
import com.mantismonitor.exception.MantisGroupDontFoundException;
import com.mantismonitor.repository.MantisGroupRepository;

@Component
public class MantisGroupService {
	
	private MantisGroupRepository mantisGroupRepository;
	private MantisService mantisService;

	public MantisGroupService(MantisGroupRepository mantisGroupRepository, MantisService mantisService) {
		this.mantisGroupRepository = mantisGroupRepository;
		this.mantisService = mantisService;
	}

	public List<MantisGroup> getAll(User user) {
		return mantisGroupRepository.findByUser(user);
	}

	public MantisGroup add(MantisGroup mantisGroup) {
		return mantisGroupRepository.save(mantisGroup);
	}
	
	public Mantis addMantis(MantisGroup mantisGroup, Long id) {
		Mantis mantis = mantisService.add(id);
		
		if (!mantisGroup.getMantisList().contains(mantis)) {
			mantisGroup.getMantisList().add(mantis);
			mantisGroupRepository.save(mantisGroup);
		}
		
		return mantis;
	}
	
	public void remove(Long id) {
		mantisGroupRepository.delete(id);
	}
	
	public void removeMantis(Long id, Long mantisId) {
		MantisGroup mantisGroup = mantisGroupRepository.findOne(id);
		
		mantisGroup.getMantisList().remove(new Mantis(mantisId));
		
		mantisGroupRepository.save(mantisGroup);
	}
	
	public MantisGroup get(Long id) {
		return mantisGroupRepository.findOne(id);
	}

	public void verify(User user, Long id) throws MantisGroupDontFoundException {
		if (mantisGroupRepository.findByUserAndId(user, id).isEmpty()) {
			throw new MantisGroupDontFoundException();
		}
	}
	
	public MantisGroup refreshAndGetMantisGroup(User user, Long mantisGroupId) {
		MantisGroup mantisGroup = mantisGroupRepository.findOne(mantisGroupId);
		
		for (Mantis mantis : mantisGroup.getMantisList()) {
			mantisService.refreshAndGetMantis(user, mantis.getId());
		}
		
		return mantisGroup;
	}

}
