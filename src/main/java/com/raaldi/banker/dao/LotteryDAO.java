package com.raaldi.banker.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.raaldi.banker.model.Lottery;

@Repository("lotteryDAO")
public class LotteryDAO extends ModelDAO<Lottery, Long> {

	private static final long serialVersionUID = 1L;

	public LotteryDAO(Class<Lottery> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
