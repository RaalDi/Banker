package com.raaldi.banker.repository;

import com.raaldi.banker.model.Currency;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("currencyRepository")
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
