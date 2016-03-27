package com.raaldi.banker.dao;

import com.raaldi.banker.model.SessionState;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("sessionStateDAO")
public class SessionStateDAO extends ModelDao<SessionState, Long> {

    private static final long serialVersionUID = 1L;

    public SessionStateDAO(Class<SessionState> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
