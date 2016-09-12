package com.raaldi.banker.service;

import com.raaldi.banker.model.RestrictPlay;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.RestrictPlayRepository;

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

/** Restrict play service provides access to the restrict play repository. */
@NoArgsConstructor
@Service("restrictPlayService")
@Transactional
public final class RestrictPlayService implements ModelService<RestrictPlay> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The restrict play repository. */
  private AbstractModelRepository<RestrictPlay, Long> repository;

  /** Initializes the restrict play repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new RestrictPlayRepository(RestrictPlay.class, entityManager);
  }

  @Override
  public void save(final RestrictPlay model) {
    repository.save(model);
  }

  @Override
  public RestrictPlay findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<RestrictPlay> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final RestrictPlay model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
