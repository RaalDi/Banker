package com.raaldi.banker.repository;

import com.raaldi.banker.model.Role;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("roleDAO")
public final class RoleRepository extends AbstractModelRepository<Role, Long> {

  public RoleRepository(final Class<Role> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
