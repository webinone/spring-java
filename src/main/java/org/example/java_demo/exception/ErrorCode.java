package org.example.java_demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  MEMBER_NOT_FOUND(404, "MEMBER NOT FOUND"),
  ;

  private final Integer statusCode;
  private final String message;

}
