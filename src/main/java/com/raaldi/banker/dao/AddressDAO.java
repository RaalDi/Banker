package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Address;

@Repository("addressDAO")
public class AddressDAO extends ModelDAO<Address, Long> {

	private static final long serialVersionUID = 1L;

	public AddressDAO(Class<Address> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
