package com.mantismonitor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mantismonitor.entity.MantisGroup;
import com.mantismonitor.entity.User;

public interface MantisGroupRepository extends CrudRepository<MantisGroup, Long> {

	public List<MantisGroup> findByUser(User user);
	public List<MantisGroup> findByUserAndId(User user, Long id);
	
}
