package com.raaldi.banker.dao;

import com.raaldi.banker.model.SessionState;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("sessionStateDAO")
public class SessionStateDAO extends AbstractModelDao<SessionState, Long> {

    public SessionStateDAO(final Class<SessionState> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
