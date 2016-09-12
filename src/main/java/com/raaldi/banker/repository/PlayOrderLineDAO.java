package com.raaldi.banker.repository;

import com.raaldi.banker.model.PlayOrderLine;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playOrderLineDAO")
public final class PlayOrderLineDAO extends AbstractModelRepository<PlayOrderLine, Long> {

  public PlayOrderLineDAO(final Class<PlayOrderLine> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
