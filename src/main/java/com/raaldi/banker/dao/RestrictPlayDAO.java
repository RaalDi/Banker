package com.raaldi.banker.dao;

import com.raaldi.banker.model.RestrictPlay;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("restrictPlayDAO")
public class RestrictPlayDAO extends AbstractModelDao<RestrictPlay, Long> {

    public RestrictPlayDAO(final Class<RestrictPlay> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
