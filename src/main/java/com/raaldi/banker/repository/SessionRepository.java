package com.raaldi.banker.repository;

import com.raaldi.banker.model.Session;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("sessionDAO")
public final class SessionRepository extends AbstractModelRepository<Session, Long> {

  public SessionRepository(final Class<Session> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
