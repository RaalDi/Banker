package com.raaldi.banker.dao;

import com.raaldi.banker.model.Company;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("companyDAO")
public final class CompanyDAO extends AbstractModelDao<Company, Long> {

  public CompanyDAO(final Class<Company> domainClass, final EntityManager em) {
    super(domainClass, em);
  }
}
