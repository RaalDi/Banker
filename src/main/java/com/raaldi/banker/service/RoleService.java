package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.RoleDAO;
import com.raaldi.banker.model.Role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("roleService")
@Transactional
public final class RoleService implements ModelService<Role> {

  @PersistenceContext
  private EntityManager em;

  private AbstractModelDao<Role, Long> entityDAO;

  @PostConstruct
  public void postConstruct() {
    entityDAO = new RoleDAO(Role.class, em);
  }

  @Override
  public void save(Role model) {
    entityDAO.save(model);
  }

  @Override
  public Role findOne(Long id) {
    return entityDAO.findOne(id);
  }

  @Override
  public List<Role> findAll() {
    return entityDAO.findAll();
  }

  @Override
  public boolean exists(Role model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(Long id) {
    return entityDAO.exists(id);
  }

}
