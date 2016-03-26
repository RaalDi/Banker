package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.CashRegister;

@Repository("cashRegisterDAO")
public class CashRegisterDAO extends ModelDAO<CashRegister, Long> {

	private static final long serialVersionUID = 1L;

	public CashRegisterDAO(Class<CashRegister> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
