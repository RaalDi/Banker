package com.raaldi.banker.service;

import com.raaldi.banker.dao.AbstractModelDao;
import com.raaldi.banker.dao.RolePermissionDAO;
import com.raaldi.banker.model.RolePermission;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("rolePermissionService")
@Transactional
public final class RolePermissionService implements ModelService<RolePermission> {

    @PersistenceContext
    private EntityManager em;

    private AbstractModelDao<RolePermission, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new RolePermissionDAO(RolePermission.class, em);
    }

    @Override
    public void save(RolePermission model) {
        entityDAO.save(model);
    }

    @Override
    public RolePermission findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<RolePermission> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(RolePermission model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }
}
