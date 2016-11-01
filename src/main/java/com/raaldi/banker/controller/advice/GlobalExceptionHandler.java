package com.raaldi.banker.controller.advice;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.raaldi.banker.security.exception.SignInException;
import com.raaldi.banker.security.exception.UserNotFoundException;
import com.raaldi.banker.security.exception.UserSignedInException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public void userNotFound() {
  }

  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler(SignInException.class)
  public void failedToSignIn() {
  }

  @ResponseStatus(FORBIDDEN)
  @ExceptionHandler(SignatureException.class)
  public void failedToVerify() {
  }

  @ExceptionHandler(UserSignedInException.class)
  public ResponseEntity<ErrorInformation> userSignedIn(HttpServletRequest request, Exception exception) {
    return new ResponseEntity<ErrorInformation>(new ErrorInformation(exception.getMessage()), HttpStatus.FORBIDDEN);
  }
}
