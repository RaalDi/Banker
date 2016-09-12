package com.raaldi.banker.repository;

import com.raaldi.banker.model.Shop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("shopDAO")
public final class ShopRepository extends AbstractModelRepository<Shop, Long> {

  public ShopRepository(final Class<Shop> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
