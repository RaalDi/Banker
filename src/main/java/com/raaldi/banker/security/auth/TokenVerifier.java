package com.raaldi.banker.security.auth;

public interface TokenVerifier {
  public boolean verify(String jti);
}
