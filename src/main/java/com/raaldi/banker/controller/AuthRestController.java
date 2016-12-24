package com.raaldi.banker.controller;

import com.raaldi.banker.security.exception.JwtAuthenticationException;
import com.raaldi.banker.security.exception.SignInException;
import com.raaldi.banker.security.exception.UserNotFoundException;
import com.raaldi.banker.security.exception.UserSignedInException;
import com.raaldi.banker.security.oauth.SignInRequest;
import com.raaldi.banker.service.JwtService;
import com.raaldi.banker.service.UserService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

/** Authenticate rest controller provides authentication. */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthRestController {

  public static final String TOKEN_HEADER = "X-Security-Token";

  /** The User Service. */
  @Getter(AccessLevel.PRIVATE)
  private final UserService userService;

  /** The JWT Service. */
  @Getter(AccessLevel.PRIVATE)
  private final JwtService jwtService;

  /** The constructor. */
  @Autowired
  public AuthRestController(final UserService service, final JwtService jwtService) {
    this.userService = service;
    this.jwtService = jwtService;
  }

  @RequestMapping("/user")
  public String user(Principal user) {
    return user.getName();
  }

  @RequestMapping(value = "/login5", method = RequestMethod.GET)
  public ResponseEntity<String> login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout) {

    return new ResponseEntity<String>("Mierda", HttpStatus.OK);

  }

  /** Authenticate users. **/
  @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
  public ResponseEntity<?> signIn(@RequestBody final SignInRequest signInRequest, final HttpServletResponse response)
      throws UserSignedInException {
    final String username = signInRequest.getUsername();

    log.info("Authenticating Usernanme {}", username);
    return userService.signIn(username, signInRequest.getPassword()).map(user -> {
      try {
        response.setHeader(TOKEN_HEADER, jwtService.token(user));

      } catch (final URISyntaxException | IOException | IllegalArgumentException exception) {
        log.error("Unable to build token for Username: {}", username, exception);
        throw new JwtAuthenticationException(username, exception);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    }).orElseThrow(() -> new SignInException(username));
  }

  /** Authenticate users. **/
  @RequestMapping(value = "/sign-out/{username}", method = RequestMethod.GET)
  public ResponseEntity<?> signOut(@PathVariable("username") final String username,
      final HttpServletResponse response) {
    return getUserService().signOut(username).map(signOutResponse -> {
      response.setHeader(TOKEN_HEADER, null);
      return new ResponseEntity<>(HttpStatus.OK);
    }).orElseThrow(() -> new UserNotFoundException(username));
  }
}
