package com.raaldi.banker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableCaching
@EnableJpaRepositories("com.raaldi.banker.repository")
@EntityScan("com.raaldi.banker.model")
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class BankerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankerApplication.class, args);
  }
}
