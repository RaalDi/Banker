package com.raaldi.banker.dao;

import com.raaldi.banker.model.Shop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("shopDAO")
public final class ShopDAO extends AbstractModelDao<Shop, Long> {

  public ShopDAO(final Class<Shop> domainClass, final EntityManager em) {
    super(domainClass, em);
  }
}
