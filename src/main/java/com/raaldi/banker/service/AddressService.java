package com.raaldi.banker.service;

import com.raaldi.banker.model.Address;
import com.raaldi.banker.repository.AddressRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Address service provides access to the address repository. */
@NoArgsConstructor
@Service("addressService")
@Transactional
public class AddressService implements ModelService<Address> {

  /** The address repository. */
  private AddressRepository repository;

  @Autowired
  public void setRepository(final AddressRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Address model) {
    repository.save(model);
  }

  @Override
  public Address findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Address> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Address model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }
}
