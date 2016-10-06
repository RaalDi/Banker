package com.raaldi.banker.repository;

import com.raaldi.banker.model.Play;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("playRepository")
public interface PlayRepository extends CrudRepository<Play, Long> {
}
