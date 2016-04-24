package com.raaldi.banker.dao;

import com.raaldi.banker.model.CashRegister;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("cashRegisterDAO")
public final class CashRegisterDAO extends AbstractModelDao<CashRegister, Long> {

    public CashRegisterDAO(final Class<CashRegister> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
