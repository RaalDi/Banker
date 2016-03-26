package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Session;

@Repository("sessionDAO")
public class SessionDAO extends ModelDAO<Session, Long> {

	private static final long serialVersionUID = 1L;

	public SessionDAO(Class<Session> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
