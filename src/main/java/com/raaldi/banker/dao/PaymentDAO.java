package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Payment;

@Repository("paymentDAO")
public class PaymentDAO extends ModelDAO<Payment, Long> {

	private static final long serialVersionUID = 1L;

	public PaymentDAO(Class<Payment> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
