package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.CashRegisterDAO;
import com.raaldi.banker.model.CashRegister;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("cashRegisterService")
@Transactional
public final class CashRegisterService implements ModelService<CashRegister> {

  @PersistenceContext
  private EntityManager em;

  private AbstractModelDao<CashRegister, Long> entityDAO;

  @PostConstruct
  public void postConstruct() {
    entityDAO = new CashRegisterDAO(CashRegister.class, em);
  }

  @Override
  public void save(CashRegister model) {
    entityDAO.save(model);
  }

  @Override
  public CashRegister findOne(Long id) {
    return entityDAO.findOne(id);
  }

  @Override
  public List<CashRegister> findAll() {
    return entityDAO.findAll();
  }

  @Override
  public boolean exists(CashRegister model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(Long id) {
    return entityDAO.exists(id);
  }

}
