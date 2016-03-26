package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.User;

@Repository("userDAO")
public class UserDAO extends ModelDAO<User, Long> {

	private static final long serialVersionUID = 1L;

	public UserDAO(Class<User> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
