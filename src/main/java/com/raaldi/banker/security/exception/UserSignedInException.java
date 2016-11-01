package com.raaldi.banker.security.exception;

import static java.lang.String.format;

public class UserSignedInException extends RuntimeException {

  private static final long serialVersionUID = 3800381526324573858L;

  public UserSignedInException(String username) {
    super(format("Username %s is already signed in", username));
  }
}
