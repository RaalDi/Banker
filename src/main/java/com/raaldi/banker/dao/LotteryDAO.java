package com.raaldi.banker.dao;

import com.raaldi.banker.model.Lottery;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository("lotteryDAO")
public class LotteryDAO extends ModelDao<Lottery, Long> {

    private static final long serialVersionUID = 1L;

    public LotteryDAO(Class<Lottery> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
