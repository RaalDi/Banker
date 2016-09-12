package com.raaldi.banker.service;

import com.raaldi.banker.model.Role;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.RoleRepository;

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

/** Role service provides access to the role repository. */
@NoArgsConstructor
@Service("roleService")
@Transactional
public final class RoleService implements ModelService<Role> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The role repository. */
  private AbstractModelRepository<Role, Long> repository;

  /** Initializes the role repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new RoleRepository(Role.class, entityManager);
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
  public List<Role> findAll() {
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
