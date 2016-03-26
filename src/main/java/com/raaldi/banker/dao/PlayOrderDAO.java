package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.PlayOrder;

@Repository("playOrderDAO")
public class PlayOrderDAO extends ModelDAO<PlayOrder, Long> {

	private static final long serialVersionUID = 1L;

	public PlayOrderDAO(Class<PlayOrder> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
