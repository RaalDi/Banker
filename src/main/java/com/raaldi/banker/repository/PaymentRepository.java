package com.raaldi.banker.repository;

import com.raaldi.banker.model.Payment;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("paymentDAO")
public final class PaymentRepository extends AbstractModelRepository<Payment, Long> {

  public PaymentRepository(final Class<Payment> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
