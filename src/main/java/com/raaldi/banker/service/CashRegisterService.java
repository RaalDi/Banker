package com.raaldi.banker.service;

import com.raaldi.banker.model.CashRegister;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.CashRegisterRepository;

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

/** Cash register service provides access to the cash register repository. */
@NoArgsConstructor
@Service("cashRegisterService")
@Transactional
public final class CashRegisterService implements ModelService<CashRegister> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The cash register repository. */
  private AbstractModelRepository<CashRegister, Long> repository;

  /** Initializes the cash register repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new CashRegisterRepository(CashRegister.class, entityManager);
  }

  @Override
  public void save(final CashRegister model) {
    repository.save(model);
  }

  @Override
  public CashRegister findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<CashRegister> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final CashRegister model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
