package com.raaldi.banker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raaldi.banker.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
@Component
public class JwtService {
  private static final String ISSUER = "com.raaldi.banker";
  private static final String USER_KEY = "user";
  private static final long EXPIRATION_HOURS = 12L;

  @Getter(AccessLevel.PRIVATE)
  private ObjectMapper objectMapper;

  @Autowired
  public JwtService(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /** Builds the Token. */
  public String token(@NonNull final User user) throws URISyntaxException, IOException, IllegalArgumentException {

    if (user.isSignedIn()) {
      throw new IllegalArgumentException("Unable to Create Token");
    }

    final LocalDateTime now = LocalDateTime.now();
    final LocalDateTime expiration = now.plusHours(EXPIRATION_HOURS);
    user.setSignedInDate(now);
    user.setTokeExpirationDate(expiration);
    user.setSignedIn(true);
    final String jsonUser = getObjectMapper().writeValueAsString(user);

    log.trace("Building TOKEN for USER: {}", user.getUsername());
    return Jwts.builder().claim(USER_KEY, jsonUser).setId(String.valueOf(user.getUserId()))
        .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC))).setSubject(user.getUsername())
        .setExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC))).setIssuer(ISSUER)
        .signWith(SignatureAlgorithm.HS512, getKey()).compact();
  }

  /** Verifies the Token. */
  public Optional<User> verify(String token) throws IOException, URISyntaxException {
    final Claims claims = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
    final String jsonUser = (String) claims.get(USER_KEY);

    return Optional.ofNullable(getObjectMapper().readValue(jsonUser, User.class));
  }

  /** Gets the signature. */
  private byte[] getKey() throws URISyntaxException, IOException {
    return Files.readAllBytes(Paths.get(JwtService.class.getResource("/jwt.key").toURI()));
  }
}
