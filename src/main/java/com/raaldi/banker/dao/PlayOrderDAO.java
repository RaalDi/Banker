package com.raaldi.banker.dao;

import com.raaldi.banker.model.PlayOrder;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playOrderDAO")
public final class PlayOrderDAO extends AbstractModelDao<PlayOrder, Long> {

    public PlayOrderDAO(final Class<PlayOrder> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
