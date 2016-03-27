package com.raaldi.banker.dao;

import com.raaldi.banker.model.RolePermission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("rolePermissionDAO")
public class RolePermissionDAO extends AbstractModelDao<RolePermission, Long> {

    public RolePermissionDAO(final Class<RolePermission> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
