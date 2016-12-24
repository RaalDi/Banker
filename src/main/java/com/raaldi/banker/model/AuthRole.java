package com.raaldi.banker.model;

import org.springframework.security.core.GrantedAuthority;

public enum AuthRole implements GrantedAuthority {
  ADMIN, MANAGER, SUPERVISOR, USER;

  @Override
  public String getAuthority() {
    return this.name();
  }
}
