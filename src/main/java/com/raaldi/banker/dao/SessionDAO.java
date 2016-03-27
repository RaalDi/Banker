package com.raaldi.banker.dao;

import com.raaldi.banker.model.Session;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("sessionDAO")
public class SessionDAO extends ModelDao<Session, Long> {

    private static final long serialVersionUID = 1L;

    public SessionDAO(Class<Session> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
