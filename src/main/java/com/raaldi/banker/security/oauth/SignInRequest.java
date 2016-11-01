package com.raaldi.banker.security.oauth;

import lombok.Data;

import java.io.Serializable;

/** The Sign In Request. */
@Data
public class SignInRequest implements Serializable {

  private static final long serialVersionUID = 7242733448979986942L;

  /** User request user name. **/
  private String username;

  /** User request password. **/
  private String password;
}
