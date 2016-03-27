package com.raaldi.banker.dao;

import com.raaldi.banker.model.CashRegister;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("cashRegisterDAO")
public class CashRegisterDAO extends ModelDao<CashRegister, Long> {

    private static final long serialVersionUID = 1L;

    public CashRegisterDAO(Class<CashRegister> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
