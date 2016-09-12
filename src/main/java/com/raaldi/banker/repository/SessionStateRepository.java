package com.raaldi.banker.repository;

import com.raaldi.banker.model.SessionState;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("sessionStateDAO")
public final class SessionStateRepository extends AbstractModelRepository<SessionState, Long> {

  public SessionStateRepository(final Class<SessionState> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
