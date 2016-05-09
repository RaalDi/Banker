package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.LotteryDAO;
import com.raaldi.banker.model.Lottery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("lotteryService")
@Transactional
public final class LotteryService implements ModelService<Lottery> {

  @PersistenceContext
  private EntityManager em;

  private AbstractModelDao<Lottery, Long> entityDAO;

  @PostConstruct
  public void postConstruct() {
    entityDAO = new LotteryDAO(Lottery.class, em);
  }

  @Override
  public void save(Lottery model) {
    entityDAO.save(model);
  }

  @Override
  public Lottery findOne(Long id) {
    return entityDAO.findOne(id);
  }

  @Override
  public List<Lottery> findAll() {
    return entityDAO.findAll();
  }

  @Override
  public boolean exists(Lottery model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(Long id) {
    return entityDAO.exists(id);
  }

}
