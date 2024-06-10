package org.example.java_demo.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.model.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "org.example.java_demo.api")
public class ExceptionAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiResponse<Void> handleException(Exception e) {

    ApiResponse<Void> response = ApiResponse.error(e.getMessage());
    return response;
  }


}
