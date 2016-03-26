package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Currency;

@Repository("currencyDAO")
public class CurrencyDAO extends ModelDAO<Currency, Long> {

	private static final long serialVersionUID = 1L;

	public CurrencyDAO(Class<Currency> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
