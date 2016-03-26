package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.LotteryDAO;
import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.model.Lottery;

@Service("lotteryService")
@Transactional
public class LotteryService implements ModelService<Lottery> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<Lottery, Long> entityDAO;

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
