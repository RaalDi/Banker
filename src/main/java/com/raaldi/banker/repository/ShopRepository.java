package com.raaldi.banker.repository;

import com.raaldi.banker.model.Shop;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("shopRepository")
public interface ShopRepository extends CrudRepository<Shop, Long> {
}
