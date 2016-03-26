package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.PlayOrderLine;

@Repository("playOrderLineDAO")
public class PlayOrderLineDAO extends ModelDAO<PlayOrderLine, Long> {

	private static final long serialVersionUID = 1L;

	public PlayOrderLineDAO(Class<PlayOrderLine> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
