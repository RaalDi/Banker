package com.raaldi.banker.repository;

import com.raaldi.banker.model.User;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@CacheConfig(cacheNames = "User")
@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
  // @Query("select u from User u left join fetch u.roles r where
  // u.username=:username")
  @Query("select u from User u where u.username=:username")
  public Optional<User> findByUsername(@Param("username") String username);

  // @Query(value = "SELECT * FROM person WHERE username=?1 AND password =
  // crypt(?2, password)", nativeQuery = true)
  @Cacheable
  @Query("select u from User u where u.username=?1 and u.password = crypt(?2, u.password)")
  public Optional<User> findByUsernameAndPassword(String username, String password);
}
