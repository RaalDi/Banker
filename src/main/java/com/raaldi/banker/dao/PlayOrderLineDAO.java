package com.raaldi.banker.dao;

import com.raaldi.banker.model.PlayOrderLine;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playOrderLineDAO")
public final class PlayOrderLineDAO extends AbstractModelDao<PlayOrderLine, Long> {

  public PlayOrderLineDAO(final Class<PlayOrderLine> domainClass, final EntityManager em) {
    super(domainClass, em);
  }
}
