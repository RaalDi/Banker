package com.raaldi.banker.test.controller;

import com.raaldi.banker.controller.AuthRestController;
import com.raaldi.banker.security.oauth.SignInRequest;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void init() {

  }

  @Test
  public void test() {
    SignInRequest request = new SignInRequest();
    request.setUsername("admin");
    request.setPassword("P@55word");
    final ResponseEntity<?> response = restTemplate.postForEntity("/auth/sign-in", request, Object.class);
    // gettForEntity("/auth/sign-in", String.class, "Phil");

    log.info("#########---------->>>>>>> Token: {} ", response.getHeaders().get(AuthRestController.TOKEN_HEADER));
  }
}
