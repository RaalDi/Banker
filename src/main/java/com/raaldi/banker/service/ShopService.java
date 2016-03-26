package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.dao.ShopDAO;
import com.raaldi.banker.model.Shop;

@Service("shopService")
@Transactional
public class ShopService implements ModelService<Shop> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<Shop, Long> entityDAO;

	@PostConstruct
	public void postConstruct() {
		entityDAO = new ShopDAO(Shop.class, em);
	}

	@Override
	public void save(Shop model) {
		entityDAO.save(model);
	}

	@Override
	public Shop findOne(Long id) {
		return entityDAO.findOne(id);
	}

	@Override
	public List<Shop> findAll() {
		return entityDAO.findAll();
	}

	@Override
	public boolean exists(Shop model) {
		return this.exists(model.getId());
	}

	@Override
	public boolean exists(Long id) {
		return entityDAO.exists(id);
	}

}
