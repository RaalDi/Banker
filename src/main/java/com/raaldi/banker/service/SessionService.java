package com.raaldi.banker.service;

import com.raaldi.banker.dao.ModelDao;
import com.raaldi.banker.dao.SessionDAO;
import com.raaldi.banker.model.Session;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("sessionService")
@Transactional
public class SessionService implements ModelService<Session> {

    @PersistenceContext
    private EntityManager em;

    private ModelDao<Session, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new SessionDAO(Session.class, em);
    }

    @Override
    public void save(Session model) {
        entityDAO.save(model);
    }

    @Override
    public Session findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<Session> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(Session model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }

}
