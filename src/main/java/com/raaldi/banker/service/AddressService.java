package com.raaldi.banker.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raaldi.banker.dao.AddressDAO;
import com.raaldi.banker.dao.ModelDAO;
import com.raaldi.banker.model.Address;

@Service("addressService")
@Transactional
public class AddressService implements ModelService<Address> {

	@PersistenceContext
	private EntityManager em;

	private ModelDAO<Address, Long> entityDAO;

	@PostConstruct
	public void postConstruct() {
		entityDAO = new AddressDAO(Address.class, em);
	}

	@Override
	public void save(Address model) {
		entityDAO.save(model);
	}

	@Override
	public Address findOne(Long id) {
		return entityDAO.findOne(id);
	}

	@Override
	public List<Address> findAll() {
		return entityDAO.findAll();
	}

	@Override
	public boolean exists(Address model) {
		return this.exists(model.getId());
	}

	@Override
	public boolean exists(Long id) {
		return entityDAO.exists(id);
	}

}
