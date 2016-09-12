package com.raaldi.banker.service;

import com.raaldi.banker.model.Company;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.CompanyRepository;

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

/** Company service provides access to the company repository. */
@NoArgsConstructor
@Service("companyService")
@Transactional
public final class CompanyService implements ModelService<Company> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The company repository. */
  private AbstractModelRepository<Company, Long> repository;

  /** Initializes the company repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new CompanyRepository(Company.class, entityManager);
  }

  @Override
  public void save(final Company model) {
    repository.save(model);
  }

  @Override
  public Company findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Company> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Company model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
