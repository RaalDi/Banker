package com.raaldi.banker.dao;

import com.raaldi.banker.model.Permission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("permissionDAO")
public final class PermissionDAO extends AbstractModelDao<Permission, Long> {

    public PermissionDAO(final Class<Permission> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
