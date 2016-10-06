package com.raaldi.banker.security.auth;

public class BankerTokenVerifier implements TokenVerifier {
  @Override
  public boolean verify(String jti) {
    return true;
  }
}