package org.example.java_demo.exception;

import lombok.Getter;

@Getter
public class ApiErrorException extends RuntimeException{

  private final ErrorCode errorCode;
  private Object[] args;

  public ApiErrorException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ApiErrorException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public ApiErrorException(ErrorCode errorCode, Object[] args) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.args = args;
  }

  public ApiErrorException(ErrorCode errorCode, String message, Object[] args) {
    super(message);
    this.errorCode = errorCode;
    this.args = args;
  }

}
