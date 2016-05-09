package com.raaldi.banker.dao;

import com.raaldi.banker.model.Address;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("addressDAO")
public final class AddressDAO extends AbstractModelDao<Address, Long> {

  public AddressDAO(final Class<Address> domainClass, final EntityManager em) {
    super(domainClass, em);
  }
}
