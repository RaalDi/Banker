package com.raaldi.banker.service;

import com.raaldi.banker.model.Lottery;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.LotteryRepository;

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

/** Lottery service provides access to the lottery repository. */
@NoArgsConstructor
@Service("lotteryService")
@Transactional
public final class LotteryService implements ModelService<Lottery> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The lottery repository. */
  private AbstractModelRepository<Lottery, Long> repository;

  /** Initializes the lottery repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new LotteryRepository(Lottery.class, entityManager);
  }

  @Override
  public void save(final Lottery model) {
    repository.save(model);
  }

  @Override
  public Lottery findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Lottery> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Lottery model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
