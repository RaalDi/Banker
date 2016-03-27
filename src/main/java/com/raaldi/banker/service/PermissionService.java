package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.PermissionDAO;
import com.raaldi.banker.model.Permission;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("permissionService")
@Transactional
public class PermissionService implements ModelService<Permission> {

    @PersistenceContext
    private EntityManager em;

    private AbstractModelDao<Permission, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new PermissionDAO(Permission.class, em);
    }

    @Override
    public void save(Permission model) {
        entityDAO.save(model);
    }

    @Override
    public Permission findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<Permission> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(Permission model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }

}
