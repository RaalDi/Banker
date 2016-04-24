package com.raaldi.banker.dao;

import com.raaldi.banker.model.Role;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("roleDAO")
public final class RoleDAO extends AbstractModelDao<Role, Long> {

    public RoleDAO(final Class<Role> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
