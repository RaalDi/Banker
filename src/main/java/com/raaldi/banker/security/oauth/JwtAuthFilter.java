package com.raaldi.banker.security.oauth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// This filter should not be enabled when using Spring Boot JWT Token.
//@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    final String authorization = request.getHeader("Authorization");
    if (authorization != null) {
      final JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
      SecurityContextHolder.getContext().setAuthentication(token);
    }
    filterChain.doFilter(request, response);
  }
}