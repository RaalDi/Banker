package com.raaldi.banker.repository;

import com.raaldi.banker.model.RolePermission;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("rolePermissionRepository")
public interface RolePermissionRepository extends CrudRepository<RolePermission, Long> {
}
