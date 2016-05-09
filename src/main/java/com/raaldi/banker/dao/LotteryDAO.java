package com.raaldi.banker.dao;

import com.raaldi.banker.model.Lottery;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("lotteryDAO")
public final class LotteryDAO extends AbstractModelDao<Lottery, Long> {

  public LotteryDAO(final Class<Lottery> domainClass, final EntityManager em) {
    super(domainClass, em);
  }
}
