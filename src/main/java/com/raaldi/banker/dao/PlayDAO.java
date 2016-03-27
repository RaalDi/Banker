package com.raaldi.banker.dao;

import com.raaldi.banker.model.Play;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playDAO")
public class PlayDAO extends ModelDao<Play, Long> {

    private static final long serialVersionUID = 1L;

    public PlayDAO(Class<Play> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
