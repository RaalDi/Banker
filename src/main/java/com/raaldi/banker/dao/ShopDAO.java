package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Shop;

@Repository("shopDAO")
public class ShopDAO extends ModelDAO<Shop, Long> {

	private static final long serialVersionUID = 1L;

	public ShopDAO(Class<Shop> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
