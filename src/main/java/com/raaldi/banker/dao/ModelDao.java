package com.raaldi.banker.dao;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class ModelDao<T extends Serializable, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ModelDao(final Class<T> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
