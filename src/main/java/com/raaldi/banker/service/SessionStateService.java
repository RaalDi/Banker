package com.raaldi.banker.service;

import com.raaldi.banker.model.SessionState;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.SessionStateRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Session state service provides access to the session state repository. */
@NoArgsConstructor
@Service("sessionStateService")
@Transactional
public final class SessionStateService implements ModelService<SessionState> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The session state repository. */
  private AbstractModelRepository<SessionState, Long> repository;

  /** Initializes the session state repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new SessionStateRepository(SessionState.class, entityManager);
  }

  @Override
  public void save(final SessionState model) {
    repository.save(model);
  }

  @Override
  public SessionState findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<SessionState> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final SessionState model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
