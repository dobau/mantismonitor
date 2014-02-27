package com.mantismonitor.repository;

import org.springframework.data.repository.CrudRepository;

import com.mantismonitor.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findByLogin(String login);

}
