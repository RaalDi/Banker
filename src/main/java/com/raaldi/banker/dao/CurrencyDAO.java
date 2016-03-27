package com.raaldi.banker.dao;

import com.raaldi.banker.model.Currency;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("currencyDAO")
public class CurrencyDAO extends AbstractModelDao<Currency, Long> {

    public CurrencyDAO(final Class<Currency> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
