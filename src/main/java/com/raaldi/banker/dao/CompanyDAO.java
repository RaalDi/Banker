package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Company;

@Repository("companyDAO")
public class CompanyDAO extends ModelDAO<Company, Long> {

	private static final long serialVersionUID = 1L;

	public CompanyDAO(Class<Company> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
