package com.raaldi.banker.dao;

import com.raaldi.banker.model.Currency;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("currencyDAO")
public class CurrencyDAO extends ModelDao<Currency, Long> {

    private static final long serialVersionUID = 1L;

    public CurrencyDAO(Class<Currency> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
