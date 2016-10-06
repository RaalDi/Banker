package com.raaldi.banker.service;

import com.raaldi.banker.model.User;
import com.raaldi.banker.repository.UserRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** User service provides access to the user repository. */
@Slf4j
@NoArgsConstructor
@Service("userService")
@Transactional
public class UserService implements ModelService<User> {

  /** The entity manager. */
  @PersistenceContext
  @Getter(AccessLevel.PRIVATE)
  private EntityManager entityManager;

  /** The user repository. */
  private UserRepository repository;

  @Autowired
  public void setRepository(final UserRepository repository) {
    this.repository = repository;
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
  public Iterable<User> findAll() {
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

  /** Find User by UserName. */
  public Optional<User> findByUsername(final String username) {
    return repository.findByUsername(username);
  }
}
