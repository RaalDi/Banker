package com.raaldi.banker.dao;

import com.raaldi.banker.model.Session;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("sessionDAO")
public final class SessionDAO extends AbstractModelDao<Session, Long> {

    public SessionDAO(final Class<Session> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
