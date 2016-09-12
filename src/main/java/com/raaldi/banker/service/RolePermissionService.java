package com.raaldi.banker.service;

import com.raaldi.banker.model.RolePermission;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.RolePermissionDAO;

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

/**
 * Role permission service provides access to the role permission repository.
 */
@NoArgsConstructor
@Service("rolePermissionService")
@Transactional
public final class RolePermissionService implements ModelService<RolePermission> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The role permission repository. */
  private AbstractModelRepository<RolePermission, Long> repository;

  /** Initializes the role permission repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new RolePermissionDAO(RolePermission.class, entityManager);
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
  public List<RolePermission> findAll() {
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
