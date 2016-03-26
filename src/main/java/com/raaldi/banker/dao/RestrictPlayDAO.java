package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.RestrictPlay;

@Repository("restrictPlayDAO")
public class RestrictPlayDAO extends ModelDAO<RestrictPlay, Long> {

	private static final long serialVersionUID = 1L;

	public RestrictPlayDAO(Class<RestrictPlay> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
