package com.raaldi.banker.security.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;

public final class AccessJwtToken implements JwtToken {

  private final String rawToken;
  @JsonIgnore
  private Claims claims;

  protected AccessJwtToken(final String token, Claims claims) {
    this.rawToken = token;
    this.claims = claims;
  }

  @Override
  public String getToken() {
    // TODO Auto-generated method stub
    return null;
  }

  public Claims getClaims() {
    return claims;
  }

}
