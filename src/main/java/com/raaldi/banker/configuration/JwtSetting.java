package com.raaldi.banker.configuration;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "banker.security.jwt")
public class JwtSetting {
  /**
   * {@link JwtToken} will expire after this time.
   */
  private int expiration;

  /**
   * Token issuer.
   */
  private String issuer;

  /**
   * Key is used to sign {@link JwtToken}.
   */
  private String key;

  /**
   * {@link JwtToken} can be refreshed during this timeframe.
   */
  private int refresh;
}
