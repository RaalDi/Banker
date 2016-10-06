package com.raaldi.banker.repository;

import com.raaldi.banker.model.Address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
public interface AddressRepository extends CrudRepository<Address, Long> {
}
