package com.raaldi.banker.service;

import com.raaldi.banker.model.Session;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.SessionRepository;

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

/** Session service provides access to the session repository. */
@NoArgsConstructor
@Service("sessionService")
@Transactional
public final class SessionService implements ModelService<Session> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The session repository. */
  private AbstractModelRepository<Session, Long> repository;

  /** Initializes the session repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new SessionRepository(Session.class, entityManager);
  }

  @Override
  public void save(final Session model) {
    repository.save(model);
  }

  @Override
  public Session findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Session> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Session model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
