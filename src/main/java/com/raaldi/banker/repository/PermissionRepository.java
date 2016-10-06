package com.raaldi.banker.repository;

import com.raaldi.banker.model.Permission;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("permissionRepository")
public interface PermissionRepository extends CrudRepository<Permission, Long> {
}
