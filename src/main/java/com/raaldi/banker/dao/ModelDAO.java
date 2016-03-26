package com.raaldi.banker.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public abstract class ModelDAO<T extends Serializable, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements Serializable {

	private static final long serialVersionUID = 1L;

	public ModelDAO(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
