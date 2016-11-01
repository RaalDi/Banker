package com.raaldi.banker.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

  private static final long serialVersionUID = -8088598256604244199L;

  public JwtAuthenticationException(String msg, Throwable throwable) {
    super(msg, throwable);
  }
}
