package com.raaldi.banker.dao;

import com.raaldi.banker.model.Shop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("shopDAO")
public class ShopDAO extends ModelDao<Shop, Long> {

    private static final long serialVersionUID = 1L;

    public ShopDAO(Class<Shop> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
