package com.raaldi.banker.security.oauth;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
// @Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    response.setStatus(SC_FORBIDDEN);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    String message;
    if (exception.getCause() != null) {
      message = exception.getCause().getMessage();
    } else {
      message = exception.getMessage();
    }
    log.error("JwtAuthenticationEntryPoint Exception:", exception);
    byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
    response.getOutputStream().write(body);
  }
}
