package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.SessionStateDAO;
import com.raaldi.banker.model.SessionState;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("sessionStateService")
@Transactional
public class SessionStateService implements ModelService<SessionState> {

    @PersistenceContext
    private EntityManager em;

    private AbstractModelDao<SessionState, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new SessionStateDAO(SessionState.class, em);
    }

    @Override
    public void save(SessionState model) {
        entityDAO.save(model);
    }

    @Override
    public SessionState findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<SessionState> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(SessionState model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }

}
