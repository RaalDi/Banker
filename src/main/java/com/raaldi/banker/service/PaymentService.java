package com.raaldi.banker.service;

import com.raaldi.banker.dao.ModelDao;
import com.raaldi.banker.dao.PaymentDAO;
import com.raaldi.banker.model.Payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("paymentService")
@Transactional
public class PaymentService implements ModelService<Payment> {

    @PersistenceContext
    private EntityManager em;

    private ModelDao<Payment, Long> entityDAO;

    @PostConstruct
    public void postConstruct() {
        entityDAO = new PaymentDAO(Payment.class, em);
    }

    @Override
    public void save(Payment model) {
        entityDAO.save(model);
    }

    @Override
    public Payment findOne(Long id) {
        return entityDAO.findOne(id);
    }

    @Override
    public List<Payment> findAll() {
        return entityDAO.findAll();
    }

    @Override
    public boolean exists(Payment model) {
        return this.exists(model.getId());
    }

    @Override
    public boolean exists(Long id) {
        return entityDAO.exists(id);
    }

}
