package com.raaldi.banker.repository;

import com.raaldi.banker.model.Lottery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("lotteryRepository")
public interface LotteryRepository extends CrudRepository<Lottery, Long> {
}
