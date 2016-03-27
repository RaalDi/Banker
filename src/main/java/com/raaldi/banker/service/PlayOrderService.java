package com.raaldi.banker.service;

import com.raaldi.banker.dao.ModelDao;
import com.raaldi.banker.dao.PlayOrderDAO;
import com.raaldi.banker.model.PlayOrder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("playOrderService")
@Transactional
public class PlayOrderService implements ModelService<PlayOrder> {

    @PersistenceContext
    private EntityManager em;

    private ModelDao<PlayOrder, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new PlayOrderDAO(PlayOrder.class, em);
    }

    @Override
    public void save(PlayOrder model) {
        entityDAO.save(model);
    }

    @Override
    public PlayOrder findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<PlayOrder> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(PlayOrder model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }

}
