package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Play;

@Repository("playDAO")
public class PlayDAO extends ModelDAO<Play, Long> {

	private static final long serialVersionUID = 1L;

	public PlayDAO(Class<Play> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
