package com.raaldi.banker.dao;

import com.raaldi.banker.model.Company;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("companyDAO")
public class CompanyDAO extends ModelDao<Company, Long> {

    private static final long serialVersionUID = 1L;

    public CompanyDAO(Class<Company> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
