package com.raaldi.banker.service;

import com.raaldi.banker.model.Lottery;
import com.raaldi.banker.repository.LotteryRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Lottery service provides access to the lottery repository. */
@NoArgsConstructor
@Service("lotteryService")
@Transactional
public class LotteryService implements ModelService<Lottery> {

  /** The lottery repository. */
  private LotteryRepository repository;

  @Autowired
  public void setRepository(final LotteryRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Lottery model) {
    repository.save(model);
  }

  @Override
  public Lottery findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Lottery> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Lottery model) {
    return this.exists(model.getLotteryId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
