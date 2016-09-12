package com.raaldi.banker.repository;

import com.raaldi.banker.model.Permission;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("permissionDAO")
public final class PermissionRepository extends AbstractModelRepository<Permission, Long> {

  public PermissionRepository(final Class<Permission> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
