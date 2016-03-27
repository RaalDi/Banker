package com.raaldi.banker.dao;

import com.raaldi.banker.model.Role;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("roleDAO")
public class RoleDAO extends ModelDao<Role, Long> {

    private static final long serialVersionUID = 1L;

    public RoleDAO(Class<Role> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
