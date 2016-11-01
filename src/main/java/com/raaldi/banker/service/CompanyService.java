package com.raaldi.banker.service;

import com.raaldi.banker.model.Company;
import com.raaldi.banker.repository.CompanyRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Company service provides access to the company repository. */
@NoArgsConstructor
@Service("companyService")
@Transactional
public class CompanyService implements ModelService<Company> {

  /** The company repository. */
  private CompanyRepository repository;

  @Autowired
  public void setRepository(final CompanyRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Company model) {
    repository.save(model);
  }

  @Override
  public Company findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Company> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Company model) {
    return this.exists(model.getCompanyId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
