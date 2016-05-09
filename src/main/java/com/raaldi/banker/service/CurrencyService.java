package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.CurrencyDAO;
import com.raaldi.banker.model.Currency;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("currencyService")
@Transactional
public final class CurrencyService implements ModelService<Currency> {

  @PersistenceContext
  private EntityManager em;

  private AbstractModelDao<Currency, Long> entityDAO;

  @PostConstruct
  public void postConstruct() {
    entityDAO = new CurrencyDAO(Currency.class, em);
  }

  @Override
  public void save(Currency model) {
    entityDAO.save(model);
  }

  @Override
  public Currency findOne(Long id) {
    return entityDAO.findOne(id);
  }

  @Override
  public List<Currency> findAll() {
    return entityDAO.findAll();
  }

  @Override
  public boolean exists(Currency model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(Long id) {
    return entityDAO.exists(id);
  }

}
