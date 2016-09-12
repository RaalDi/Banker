package com.raaldi.banker.repository;

import com.raaldi.banker.model.Company;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("companyDAO")
public final class CompanyRepository extends AbstractModelRepository<Company, Long> {

  public CompanyRepository(final Class<Company> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
