package com.mantismonitor.controller;

import static br.com.caelum.vraptor.view.Results.*;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.mantismonitor.entity.Mantis;
import com.mantismonitor.entity.MantisGroup;
import com.mantismonitor.entity.User;
import com.mantismonitor.exception.MantisGroupDontFoundException;
import com.mantismonitor.service.MantisGroupService;
import com.mantismonitor.util.UserSession;

@Resource
public class MantisGroupController {

	private Result result;
	private User user;
	private MantisGroupService mantisGroupService;
	
	public MantisGroupController(MantisGroupService mantisGroupService, UserSession userSession, Result result) {
		this.result = result;
		this.mantisGroupService = mantisGroupService;
		this.user = userSession.getUser();
	}
	
	@Path("/all")
	public void getAll() {
		List<MantisGroup> mantisGroupList = mantisGroupService.getAll(user);
		
		result.include("mantisGroupList", mantisGroupList);
	}

	@Post("/add")
	public void add(MantisGroup mantisGroup) {
		mantisGroup.setUser(user);
		mantisGroup = mantisGroupService.add(mantisGroup);
		
		result.include("success", "Group saved with success");
		result.include("mantisGroup", mantisGroup);
		
		result.use(json()).withoutRoot().from(result.included()).serialize();
	}

	@Delete("/{groupId}")
	public void remove(Long groupId) throws MantisGroupDontFoundException {
		mantisGroupService.verify(user, groupId);
		mantisGroupService.remove(groupId);

		result.include("success", "Group removed with success");
		
		result.use(json()).withoutRoot().from(result.included()).serialize();
	}
	
	@Delete("/{groupId}/remove/{mantisId}")
	public void remove(Long groupId, Long mantisId) throws MantisGroupDontFoundException {
		mantisGroupService.verify(user, groupId);
		mantisGroupService.removeMantis(groupId, mantisId);
		
		result.include("success", "Mantis removed from group with success");
		
		result.use(json()).withoutRoot().from(result.included()).serialize();
	}

	@Post("/{groupId}/add/{mantisId}")
	public void add(Long groupId, Long mantisId) {
		MantisGroup mantisGroup = mantisGroupService.get(groupId);
		
		Mantis mantis = mantisGroupService.addMantis(mantisGroup, mantisId);
		result.include("mantis", mantis);
		result.include("mantisGroup", mantisGroup);
		result.include("success", "Mantis added with success");
		
		result.use(json()).withoutRoot().from(result.included()).serialize();
	}

	@Path("/{groupId}/refresh")
	public void refresh(Long groupId) {
		MantisGroup mantisGroup = mantisGroupService.refreshAndGetMantisGroup(user, groupId);
		
		result.include("success", "Mantis group refresh with success");
		result.include("mantisGroup", mantisGroup);
		
		result.use(json()).from(mantisGroup).include("mantisList").serialize();
	}
	
}
