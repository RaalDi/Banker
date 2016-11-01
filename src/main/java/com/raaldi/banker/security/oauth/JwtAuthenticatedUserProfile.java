package com.raaldi.banker.security.oauth;

import com.raaldi.banker.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticatedUserProfile implements Authentication {

  private static final long serialVersionUID = -1368494291417770461L;
  private final User user;
  private boolean authenticated;

  public JwtAuthenticatedUserProfile(final User user, final boolean authenticated) {
    this.user = user;
    this.authenticated = authenticated;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
  }

  @Override
  public Object getCredentials() {
    return user.getPassword();
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return user.getUsername();
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(final boolean authenticated) throws IllegalArgumentException {
    this.authenticated = authenticated;
  }

  @Override
  public String getName() {
    return String.join(" ", user.getFirstName(), user.getLastName());
  }
}
