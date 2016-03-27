package com.raaldi.banker.dao;

import com.raaldi.banker.model.RolePermission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("rolePermissionDAO")
public class RolePermissionDAO extends ModelDao<RolePermission, Long> {

    private static final long serialVersionUID = 1L;

    public RolePermissionDAO(Class<RolePermission> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
