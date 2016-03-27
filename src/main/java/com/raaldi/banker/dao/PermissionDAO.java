package com.raaldi.banker.dao;

import com.raaldi.banker.model.Permission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("permissionDAO")
public class PermissionDAO extends ModelDao<Permission, Long> {

    private static final long serialVersionUID = 1L;

    public PermissionDAO(final Class<Permission> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
