package com.raaldi.banker.security.oauth;

import com.raaldi.banker.model.User;
import com.raaldi.banker.security.exception.JwtAuthenticationException;
import com.raaldi.banker.service.JwtService;

import lombok.AccessLevel;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  /** The JWT Service. */
  @Getter(AccessLevel.PRIVATE)
  private JwtService jwtService;

  @Autowired
  public JwtAuthenticationProvider(final JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      final Optional<User> user = getJwtService().verify((String) authentication.getCredentials());
      return new JwtAuthenticatedUser(user.orElse(new User()), user.isPresent());
    } catch (Exception exception) {
      throw new JwtAuthenticationException("Failed to verify token", exception);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthToken.class.equals(authentication);
  }
}
