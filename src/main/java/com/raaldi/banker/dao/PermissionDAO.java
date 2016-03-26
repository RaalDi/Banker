package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Permission;

@Repository("permissionDAO")
public class PermissionDAO extends ModelDAO<Permission, Long> {

	private static final long serialVersionUID = 1L;

	public PermissionDAO(Class<Permission> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
