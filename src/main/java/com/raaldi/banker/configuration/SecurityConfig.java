package com.raaldi.banker.configuration;

import com.raaldi.banker.security.oauth.JwtAuthFilter;
import com.raaldi.banker.security.oauth.JwtAuthenticationEntryPoint;
import com.raaldi.banker.security.oauth.JwtAuthenticationProvider;
import com.raaldi.banker.util.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthFilter jwtAuthFilter;

  @Autowired
  private JwtAuthenticationProvider jwtAuthenticationProvider;

  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthEndPoint;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(jwtAuthenticationProvider);
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    final String[] publicMatchers = new String[] { "/", "/auth/sign-in" };
    final String[] managerMatchers = new String[] { "/users/**", "/lotteries/**", "/plays/**", "/currencies/**",
        "/shops/**" };
    final String[] supervisorMatchers = new String[] { "/users/**" };

    // http.csrf().ignoringAntMatchers(patterns);
    http.csrf().disable();
    // HttpMethod.OPTIONS solved CORS errors when signing in and out
    // http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(publicMatchers).permitAll()
    // .antMatchers("/**/*").hasAuthority(Role.ADMIN.name()).and()
    // .addFilterBefore(jwtAuthFilter,
    // UsernamePasswordAuthenticationFilter.class).exceptionHandling()
    // .authenticationEntryPoint(jwtAuthEndPoint);

    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(publicMatchers).permitAll()
        .antMatchers("/auth/sign-out/*").fullyAuthenticated().antMatchers("/**/*").hasAuthority(Role.ADMIN.name())
        .antMatchers(managerMatchers).hasAuthority(Role.MANAGER.name()).antMatchers(supervisorMatchers)
        .hasAuthority(Role.SUPERVISOR.name()).anyRequest().fullyAuthenticated().and()
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
        .authenticationEntryPoint(jwtAuthEndPoint);

    // HTTP Session
    // http.sessionManagement().sessionFixation().none().maximumSessions(1).maxSessionsPreventsLogin(true);
  }
}
