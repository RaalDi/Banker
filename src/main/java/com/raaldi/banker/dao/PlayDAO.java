package com.raaldi.banker.dao;

import com.raaldi.banker.model.Play;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playDAO")
public final class PlayDAO extends AbstractModelDao<Play, Long> {

  public PlayDAO(final Class<Play> domainClass, final EntityManager em) {
    super(domainClass, em);
  }
}
