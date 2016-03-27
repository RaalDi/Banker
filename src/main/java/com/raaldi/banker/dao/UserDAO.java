package com.raaldi.banker.dao;

import com.raaldi.banker.model.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("userDAO")
public class UserDAO extends AbstractModelDao<User, Long> {

    public UserDAO(final Class<User> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
