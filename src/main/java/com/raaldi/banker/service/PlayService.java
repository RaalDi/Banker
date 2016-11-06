package com.raaldi.banker.service;

import com.raaldi.banker.model.Play;
import com.raaldi.banker.repository.PlayRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Play service provides access to the play repository. */
@NoArgsConstructor
@Service("playService")
@Transactional
public class PlayService implements ModelService<Play> {

  /** The play repository. */
  private PlayRepository repository;

  @Autowired
  public void setRepository(final PlayRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Play model) {
    repository.save(model);
  }

  @Override
  public Play findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Play> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Play model) {
    return this.exists(model.getPlayId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
