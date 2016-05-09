package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.PlayDAO;
import com.raaldi.banker.model.Play;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("playService")
@Transactional
public final class PlayService implements ModelService<Play> {

  @PersistenceContext
  private EntityManager em;

  private AbstractModelDao<Play, Long> entityDAO;

  @PostConstruct
  public void postConstruct() {
    entityDAO = new PlayDAO(Play.class, em);
  }

  @Override
  public void save(Play model) {
    entityDAO.save(model);
  }

  @Override
  public Play findOne(Long id) {
    return entityDAO.findOne(id);
  }

  @Override
  public List<Play> findAll() {
    return entityDAO.findAll();
  }

  @Override
  public boolean exists(Play model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(Long id) {
    return entityDAO.exists(id);
  }

}
