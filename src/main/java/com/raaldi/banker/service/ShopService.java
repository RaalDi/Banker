package com.raaldi.banker.service;

import com.raaldi.banker.model.Shop;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.ShopRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Shop service provides access to the shop repository. */
@NoArgsConstructor
@Service("shopService")
@Transactional
public final class ShopService implements ModelService<Shop> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The shop repository. */
  private AbstractModelRepository<Shop, Long> repository;

  /** Initializes the shop repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new ShopRepository(Shop.class, entityManager);
  }

  @Override
  public void save(final Shop model) {
    repository.save(model);
  }

  @Override
  public Shop findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Shop> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Shop model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
