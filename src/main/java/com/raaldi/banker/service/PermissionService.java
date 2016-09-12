package com.raaldi.banker.service;

import com.raaldi.banker.model.Permission;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Permission service provides access to the permission repository. */
@NoArgsConstructor
@Service("permissionService")
@Transactional
public final class PermissionService implements ModelService<Permission> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The permission repository. */
  private AbstractModelRepository<Permission, Long> repository;

  /** Initializes the permission repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new PermissionRepository(Permission.class, entityManager);
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
  public List<Permission> findAll() {
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
