package com.raaldi.banker.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class AbstractModelRepository<T extends Serializable, ID extends Serializable>
    extends SimpleJpaRepository<T, ID> {

  public AbstractModelRepository(final Class<T> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
