package com.raaldi.banker.repository;

import com.raaldi.banker.model.RolePermission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("rolePermissionDAO")
public final class RolePermissionDAO extends AbstractModelRepository<RolePermission, Long> {

  public RolePermissionDAO(final Class<RolePermission> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
