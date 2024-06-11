package org.example.java_demo.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.exception.BusinessException;
import org.example.java_demo.exception.ErrorCode;
import org.example.java_demo.model.api.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "org.example.java_demo.api")
public class ExceptionAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return ErrorResponse.of(ErrorCode.SCHEMA_VALIDATION_ERROR, e.getMessage());
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
   var errorResponse = ErrorResponse.of(e.getErrorCode(), e.getMessage());
   return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getErrorCode().getStatusCode()));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(Exception e) {
    return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
  }
}
