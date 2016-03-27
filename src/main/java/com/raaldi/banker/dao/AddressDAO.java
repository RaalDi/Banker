package com.raaldi.banker.dao;

import com.raaldi.banker.model.Address;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("addressDAO")
public class AddressDAO extends ModelDao<Address, Long> {

    private static final long serialVersionUID = 1L;

    public AddressDAO(Class<Address> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
