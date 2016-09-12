package com.raaldi.banker.repository;

import com.raaldi.banker.model.Play;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("playDAO")
public final class PlayRepository extends AbstractModelRepository<Play, Long> {

  public PlayRepository(final Class<Play> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
