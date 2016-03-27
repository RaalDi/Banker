package com.raaldi.banker.dao;

import com.raaldi.banker.model.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("userDAO")
public class UserDAO extends ModelDao<User, Long> {

    private static final long serialVersionUID = 1L;

    public UserDAO(Class<User> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
