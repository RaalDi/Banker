package com.raaldi.banker.repository;

import com.raaldi.banker.model.RestrictPlay;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("restrictPlayDAO")
public final class RestrictPlayRepository extends AbstractModelRepository<RestrictPlay, Long> {

  public RestrictPlayRepository(final Class<RestrictPlay> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
