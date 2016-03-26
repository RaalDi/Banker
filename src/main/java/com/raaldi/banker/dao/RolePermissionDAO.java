package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.RolePermission;

@Repository("rolePermissionDAO")
public class RolePermissionDAO extends ModelDAO<RolePermission, Long> {

	private static final long serialVersionUID = 1L;

	public RolePermissionDAO(Class<RolePermission> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
