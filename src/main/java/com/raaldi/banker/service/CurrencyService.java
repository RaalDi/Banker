package com.raaldi.banker.service;

import com.raaldi.banker.model.Currency;
import com.raaldi.banker.repository.CurrencyRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Currency service provides access to the currency repository. */
@NoArgsConstructor
@Service("currencyService")
@Transactional
public class CurrencyService implements ModelService<Currency> {

  /** The currency repository. */
  private CurrencyRepository repository;

  @Autowired
  public void setRepository(final CurrencyRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Currency model) {
    repository.save(model);
  }

  @Override
  public Currency findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Currency> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Currency model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
