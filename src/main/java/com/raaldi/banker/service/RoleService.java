package com.raaldi.banker.service;

import com.raaldi.banker.model.Role;
import com.raaldi.banker.repository.RoleRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Role service provides access to the role repository. */
@NoArgsConstructor
@Service("roleService")
@Transactional
public class RoleService implements ModelService<Role> {

  /** The role repository. */
  private RoleRepository repository;

  @Autowired
  public void setRepository(final RoleRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Role model) {
    repository.save(model);
  }

  @Override
  public Role findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Role> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Role model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
