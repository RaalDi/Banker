package com.raaldi.banker.dao;

import com.raaldi.banker.model.RestrictPlay;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("restrictPlayDAO")
public class RestrictPlayDAO extends ModelDao<RestrictPlay, Long> {

    private static final long serialVersionUID = 1L;

    public RestrictPlayDAO(Class<RestrictPlay> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
