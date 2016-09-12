package com.raaldi.banker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

  // void delete(T model);

  List<T> findAll();

  Optional<T> find(ID id);

  // Optional<T> find(T model);

  // T save(T model);

  // void update(T model);

}
