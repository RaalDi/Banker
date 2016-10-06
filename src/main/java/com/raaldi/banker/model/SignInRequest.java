package com.raaldi.banker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequest {

  /** User request user name. **/
  private String username;

  /** User request password. **/
  private String password;

  @JsonCreator
  public SignInRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
    this.username = username;
    this.password = password;
  }
}
