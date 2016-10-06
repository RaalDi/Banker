package com.raaldi.banker.repository;

import com.raaldi.banker.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
  // @Query("select u from User u left join fetch u.roles r where
  // u.username=:username")
  @Query("select u from User u where u.username=:username")
  public Optional<User> findByUsername(@Param("username") String username);
}
