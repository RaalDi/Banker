package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.SessionState;

@Repository("sessionStateDAO")
public class SessionStateDAO extends ModelDAO<SessionState, Long> {

	private static final long serialVersionUID = 1L;

	public SessionStateDAO(Class<SessionState> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
