package com.raaldi.banker.dao;

import com.raaldi.banker.model.PlayOrderLine;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playOrderLineDAO")
public class PlayOrderLineDAO extends ModelDao<PlayOrderLine, Long> {

    private static final long serialVersionUID = 1L;

    public PlayOrderLineDAO(Class<PlayOrderLine> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
