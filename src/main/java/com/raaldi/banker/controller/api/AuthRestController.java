package com.raaldi.banker.controller.api;

import com.raaldi.banker.model.SignInRequest;
import com.raaldi.banker.model.User;
import com.raaldi.banker.security.auth.InvalidJwtTokenException;
import com.raaldi.banker.security.auth.JwtSettings;
import com.raaldi.banker.security.auth.JwtToken;
import com.raaldi.banker.security.auth.JwtTokenFactory;
import com.raaldi.banker.security.auth.RawAccessJwtToken;
import com.raaldi.banker.security.auth.RefreshToken;
import com.raaldi.banker.security.auth.TokenExtractor;
import com.raaldi.banker.security.auth.TokenVerifier;
import com.raaldi.banker.security.auth.UserContext;
import com.raaldi.banker.security.auth.WebSecurityConfig;
import com.raaldi.banker.service.UserService;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Authenticate rest controller provides authentication. */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthRestController {

  @Autowired
  private JwtTokenFactory tokenFactory;
  @Autowired
  private JwtSettings jwtSettings;
  @Autowired
  private UserService userService;
  @Autowired
  private TokenVerifier tokenVerifier;
  @Autowired
  @Qualifier("jwtHeaderTokenExtractor")
  private TokenExtractor tokenExtractor;

  @Getter(AccessLevel.PRIVATE)
  UserService service;

  private ClientDetailsService clientDetailsService;

  @Autowired
  public AuthRestController(final UserService service) {
    this.service = service;
  }

  /** Authenticate users. **/
  @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
  public ResponseEntity<User> signIn(@RequestBody final SignInRequest authRequest) {

    log.info("User Nanme {} Auth time: {}", authRequest.getUsername(), Instant.now());
    Optional<User> user = getService().findByUsername(authRequest.getUsername());

    if (user.isPresent()) {
      log.info("User name {} found.", user.get().getUsername());

      return new ResponseEntity<User>(user.get(), HttpStatus.OK);

    }
    log.info("User name {} not found.", user.get().getUsername());
    return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
  }

  @RequestMapping("/oauth/confirm_access")
  public ModelAndView getAccessConfirmation(@ModelAttribute AuthorizationRequest clientAuth) throws Exception {
    ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
    TreeMap<String, Object> model = new TreeMap<String, Object>();
    model.put("auth_request", clientAuth);
    model.put("client", client);
    return new ModelAndView("access_confirmation", model);
  }

  @Autowired
  public void setClientDetailsService(ClientDetailsService clientDetailsService) {
    this.clientDetailsService = clientDetailsService;
  }

  @RequestMapping(value = "/token", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
  public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));

    RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
    RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey())
        .orElseThrow(() -> new InvalidJwtTokenException());

    String jti = refreshToken.getJti();
    if (!tokenVerifier.verify(jti)) {
      throw new InvalidJwtTokenException();
    }

    String subject = refreshToken.getSubject();
    User user = userService.findByUsername(subject)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

    if (user.getRoles() == null)
      throw new InsufficientAuthenticationException("User has no roles assigned");
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority())).collect(Collectors.toList());

    UserContext userContext = UserContext.create(user.getUsername(), authorities);

    return tokenFactory.createAccessJwtToken(userContext);
  }
}
