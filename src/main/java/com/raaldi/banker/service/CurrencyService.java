package com.raaldi.banker.service;

import com.raaldi.banker.model.Currency;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.CurrencyRepository;

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

/** Currency service provides access to the currency repository. */
@NoArgsConstructor
@Service("currencyService")
@Transactional
public final class CurrencyService implements ModelService<Currency> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The currency repository. */
  private AbstractModelRepository<Currency, Long> repository;

  /** Initializes the currency repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new CurrencyRepository(Currency.class, entityManager);
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
  public List<Currency> findAll() {
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
