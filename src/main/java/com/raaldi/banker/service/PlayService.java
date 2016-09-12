package com.raaldi.banker.service;

import com.raaldi.banker.model.Play;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.PlayRepository;

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

/** Play service provides access to the play repository. */
@NoArgsConstructor
@Service("playService")
@Transactional
public final class PlayService implements ModelService<Play> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The play repository. */
  private AbstractModelRepository<Play, Long> repository;

  /** Initializes the play repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new PlayRepository(Play.class, entityManager);
  }

  @Override
  public void save(final Play model) {
    repository.save(model);
  }

  @Override
  public Play findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Play> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Play model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
