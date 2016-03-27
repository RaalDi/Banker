package com.raaldi.banker.dao;

import com.raaldi.banker.model.Payment;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("paymentDAO")
public class PaymentDAO extends ModelDao<Payment, Long> {

    private static final long serialVersionUID = 1L;

    public PaymentDAO(Class<Payment> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
