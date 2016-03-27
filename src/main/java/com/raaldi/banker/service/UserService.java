package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.UserDAO;
import com.raaldi.banker.model.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("userService")
@Transactional
public class UserService implements ModelService<User> {

    @PersistenceContext
    private EntityManager em;

    private AbstractModelDao<User, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new UserDAO(User.class, em);
    }

    @Override
    public void save(User model) {
        entityDAO.save(model);
    }

    @Override
    public User findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(User model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }
}
