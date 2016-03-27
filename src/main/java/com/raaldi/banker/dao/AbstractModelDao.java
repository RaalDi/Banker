package com.raaldi.banker.dao;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class AbstractModelDao<T extends Serializable, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> {

    public AbstractModelDao(final Class<T> domainClass, final EntityManager em) {
        super(domainClass, em);
    }
}
