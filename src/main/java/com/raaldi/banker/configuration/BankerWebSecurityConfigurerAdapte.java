package com.raaldi.banker.configuration;

import com.raaldi.banker.security.oauth.BankerAuthenticationProvider;
import com.raaldi.banker.util.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
// @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class BankerWebSecurityConfigurerAdapte extends WebSecurityConfigurerAdapter {

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  private BankerAuthenticationProvider bankerAuthenticationProvider;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(bankerAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    final String[] publicMatchers = new String[] { "/", "/auth/sign-in", "/oauth/token" };
    final String[] managerMatchers = new String[] { "/users/**", "/lotteries/**", "/plays/**", "/currencies/**",
        "/shops/**" };
    final String[] supervisorMatchers = new String[] { "/users/**" };

    http.csrf().disable();
    // http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(publicMatchers).permitAll()
    // .antMatchers("/auth/sign-out/*").fullyAuthenticated().antMatchers("/**/*").hasAuthority(Role.ADMIN.name())
    // .antMatchers(managerMatchers).hasAuthority(Role.MANAGER.name()).antMatchers(supervisorMatchers)
    // .hasAuthority(Role.SUPERVISOR.name()).anyRequest().fullyAuthenticated();

    http.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(publicMatchers).permitAll()
        .antMatchers("/auth/sign-out/*").authenticated().antMatchers("/**/*").hasAuthority(Role.ADMIN.name())
        .antMatchers(managerMatchers).hasAuthority(Role.MANAGER.name()).antMatchers(supervisorMatchers)
        .hasAuthority(Role.SUPERVISOR.name()).anyRequest().authenticated();
  }
}

// curl -XPOST "banker:@localhost:8080/api/oauth/token" -d
// "grant_type=password&username=admin&password=P@55word"