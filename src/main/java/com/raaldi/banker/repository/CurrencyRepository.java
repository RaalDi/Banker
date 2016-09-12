package com.raaldi.banker.repository;

import com.raaldi.banker.model.Currency;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("currencyDAO")
public final class CurrencyRepository extends AbstractModelRepository<Currency, Long> {

  public CurrencyRepository(final Class<Currency> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
