package com.raaldi.banker.repository;

import com.raaldi.banker.model.Lottery;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("lotteryDAO")
public final class LotteryRepository extends AbstractModelRepository<Lottery, Long> {

  public LotteryRepository(final Class<Lottery> domainClass, final EntityManager entityManager) {
    super(domainClass, entityManager);
  }
}
