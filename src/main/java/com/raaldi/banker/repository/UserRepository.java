package com.raaldi.banker.repository;

import com.raaldi.banker.model.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("userDAO")
public final class UserRepository extends AbstractModelRepository<User, Long> {

  public UserRepository(final Class<User> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
