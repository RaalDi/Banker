package com.raaldi.banker.security.auth;

import org.springframework.security.authentication.AuthenticationServiceException;

public class AuthMethodNotSupportedException extends AuthenticationServiceException {

  private static final long serialVersionUID = 8046070629837196108L;

  public AuthMethodNotSupportedException(String msg) {
    super(msg);
  }
}
