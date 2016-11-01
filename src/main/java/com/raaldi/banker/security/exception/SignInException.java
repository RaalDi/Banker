package com.raaldi.banker.security.exception;

import static java.lang.String.format;

public class SignInException extends RuntimeException {

  private static final long serialVersionUID = -5804479859717210347L;

  public SignInException(final String username) {
    super(format("Failed to Sign In Username %s", username));
  }
}
