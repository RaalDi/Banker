package com.raaldi.banker.dao;

import com.raaldi.banker.model.Payment;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("paymentDAO")
public class PaymentDAO extends AbstractModelDao<Payment, Long> {

    public PaymentDAO(final Class<Payment> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
