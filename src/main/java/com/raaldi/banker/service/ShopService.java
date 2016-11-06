package com.raaldi.banker.service;

import com.raaldi.banker.model.Shop;
import com.raaldi.banker.repository.ShopRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Shop service provides access to the shop repository. */
@NoArgsConstructor
@Service("shopService")
@Transactional
public class ShopService implements ModelService<Shop> {

  /** The shop repository. */
  private ShopRepository repository;

  @Autowired
  public void setRepository(final ShopRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Shop model) {
    repository.save(model);
  }

  @Override
  public Shop findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Shop> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Shop model) {
    return this.exists(model.getShopId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
