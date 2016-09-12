package com.raaldi.banker.repository;

import com.raaldi.banker.model.PlayOrder;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playOrderDAO")
public final class PlayOrderDAO extends AbstractModelRepository<PlayOrder, Long> {

  public PlayOrderDAO(final Class<PlayOrder> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
