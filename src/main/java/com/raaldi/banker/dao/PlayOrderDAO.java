package com.raaldi.banker.dao;

import com.raaldi.banker.model.PlayOrder;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playOrderDAO")
public class PlayOrderDAO extends ModelDao<PlayOrder, Long> {

    private static final long serialVersionUID = 1L;

    public PlayOrderDAO(Class<PlayOrder> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
