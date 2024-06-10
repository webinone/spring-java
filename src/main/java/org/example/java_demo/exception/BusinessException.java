package org.example.java_demo.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorCode errorCode;
  private Object[] args;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode, Object[] args) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.args = args;
  }

  public BusinessException(ErrorCode errorCode, String message, Object[] args) {
    super(message);
    this.errorCode = errorCode;
    this.args = args;
  }

}
