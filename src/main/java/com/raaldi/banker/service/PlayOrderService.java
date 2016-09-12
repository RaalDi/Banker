package com.raaldi.banker.service;

import com.raaldi.banker.model.PlayOrder;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.PlayOrderDAO;

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

/** Play order service provides access to the play order repository. */
@NoArgsConstructor
@Service("playOrderService")
@Transactional
public final class PlayOrderService implements ModelService<PlayOrder> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The play order repository. */
  private AbstractModelRepository<PlayOrder, Long> repository;

  /** Initializes the play order repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new PlayOrderDAO(PlayOrder.class, entityManager);
  }

  @Override
  public void save(final PlayOrder model) {
    repository.save(model);
  }

  @Override
  public PlayOrder findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<PlayOrder> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final PlayOrder model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
