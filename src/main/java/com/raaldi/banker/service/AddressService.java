package com.raaldi.banker.service;

import com.raaldi.banker.model.Address;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.AddressRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Address service provides access to the address repository. */
@NoArgsConstructor
@Service("addressService")
@Transactional
public final class AddressService implements ModelService<Address> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The address repository. */
  private AbstractModelRepository<Address, Long> repository;

  /** Initializes the address repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new AddressRepository(Address.class, entityManager);
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
  public List<Address> findAll() {
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
