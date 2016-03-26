package com.raaldi.banker.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	// void delete(T model);

	List<T> findAll();

	Optional<T> find(ID id);

	// Optional<T> find(T model);

	// T save(T model);

	// void update(T model);

}
