package com.raaldi.banker.service;

import com.raaldi.banker.model.User;
import com.raaldi.banker.repository.AbstractModelRepository;
import com.raaldi.banker.repository.UserRepository;

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

/** User service provides access to the user repository. */
@NoArgsConstructor
@Service("userService")
@Transactional
public final class UserService implements ModelService<User> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The user repository. */
  private AbstractModelRepository<User, Long> repository;

  /** Initializes the user repository. */
  @PostConstruct
  public void postConstruct() {
    repository = new UserRepository(User.class, entityManager);
  }

  @Override
  public void save(final User model) {
    repository.save(model);
  }

  @Override
  public User findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public List<User> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final User model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }
}
