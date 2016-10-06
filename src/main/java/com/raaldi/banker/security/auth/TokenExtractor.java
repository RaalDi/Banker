package com.raaldi.banker.security.auth;

public interface TokenExtractor {
  public String extract(String payload);
}
