package com.raaldi.banker.service;

import com.raaldi.banker.model.Permission;
import com.raaldi.banker.repository.PermissionRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Permission service provides access to the permission repository. */
@NoArgsConstructor
@Service("permissionService")
@Transactional
public class PermissionService implements ModelService<Permission> {

  /** The permission repository. */
  private PermissionRepository repository;

  @Autowired
  public void setRepository(final PermissionRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Permission model) {
    repository.save(model);
  }

  @Override
  public Permission findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Permission> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Permission model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
