package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Role;

@Repository("roleDAO")
public class RoleDAO extends ModelDAO<Role, Long> {

	private static final long serialVersionUID = 1L;

	public RoleDAO(Class<Role> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
