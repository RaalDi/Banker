package com.raaldi.banker.service;

import com.raaldi.banker.model.User;
import com.raaldi.banker.repository.UserRepository;
import com.raaldi.banker.security.exception.SignInException;
import com.raaldi.banker.security.exception.UserNotFoundException;
import com.raaldi.banker.security.exception.UserSignedInException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/** User service provides access to the user repository. */
@Slf4j
@Service("userService")
@Transactional
public class UserService {

  /** The user repository. */
  private UserRepository repository;

  @Autowired
  public UserService(final UserRepository repository) {
    this.repository = repository;
  }

  public void save(final User model) {
    repository.save(model);
  }

  public User findOne(final long id) {
    return repository.findOne(id);
  }

  public Iterable<User> findAll() {
    return repository.findAll();
  }

  public boolean exists(final User model) {
    return this.exists(model.getUserId());
  }

  public boolean exists(final long id) {
    return repository.exists(id);
  }

  /** Find User by UserName. */
  public Optional<User> findByUsername(final String username) {
    return repository.findByUsername(username);
  }

  /** Find User by UserName. */
  private Optional<User> findByUsernameAndPassword(final String username, final String password) {
    return repository.findByUsernameAndPassword(username, password);
  }

  /** Authenticate Users. */
  public Optional<User> signIn(final String username, final String password) {
    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
      log.info("Invalid Username {} or Password", username);
      throw new SignInException(username);
    }

    log.info("Finding Usernanme {}", username);
    return findByUsernameAndPassword(username, password).map(user -> {
      if (user.isSignedIn()) {
        log.info("Username {} is already signed in", username);
        throw new UserSignedInException(username);
      }
      log.info("Signed In Username {}.", username);
      return Optional.of(user);
    }).orElseThrow(() -> new SignInException(username));

  }

  /** Sign Out Users. */
  public Optional<String> signOut(final String username) {
    log.info("Signing Out Usernanme {}", username);
    return findByUsername(username).map(user -> {
      user.setSignedIn(false);
      return Optional.of(username);
    }).orElseThrow(() -> new UserNotFoundException(username));
  }
}
