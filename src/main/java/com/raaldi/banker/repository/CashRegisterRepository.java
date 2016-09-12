package com.raaldi.banker.repository;

import com.raaldi.banker.model.CashRegister;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("cashRegisterDAO")
public final class CashRegisterRepository extends AbstractModelRepository<CashRegister, Long> {

  public CashRegisterRepository(final Class<CashRegister> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
