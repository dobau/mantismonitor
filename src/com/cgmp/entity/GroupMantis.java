package com.cgmp.entity;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class GroupMantis {

	private String name;
	private List<Mantis> mantisList;
	private User user;
	
}
