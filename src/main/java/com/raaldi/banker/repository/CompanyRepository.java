package com.raaldi.banker.repository;

import com.raaldi.banker.model.Company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
