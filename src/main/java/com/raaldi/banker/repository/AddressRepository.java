package com.raaldi.banker.repository;

import com.raaldi.banker.model.Address;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("addressDAO")
public final class AddressRepository extends AbstractModelRepository<Address, Long> {

  public AddressRepository(final Class<Address> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
