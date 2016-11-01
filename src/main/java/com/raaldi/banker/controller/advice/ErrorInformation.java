package com.raaldi.banker.controller.advice;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorInformation {

  @NonNull
  private String error;
}
