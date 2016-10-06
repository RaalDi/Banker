package com.raaldi.banker.service;

import com.raaldi.banker.model.RolePermission;
import com.raaldi.banker.repository.RolePermissionRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Role permission service provides access to the role permission repository.
 */
@NoArgsConstructor
@Service("rolePermissionService")
@Transactional
public class RolePermissionService implements ModelService<RolePermission> {

  /** The role permission repository. */
  private RolePermissionRepository repository;

  @Autowired
  public void setRepository(final RolePermissionRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final RolePermission model) {
    repository.save(model);
  }

  @Override
  public RolePermission findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<RolePermission> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final RolePermission model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }
}
