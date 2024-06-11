package org.example.java_demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  MEMBER_NOT_FOUND(404, "MEMBER NOT FOUND"),
  MEMBER_ALREADY_EXISTS(400, "MEMBER ALREADY EXISTS"),
  UNAUTHORIZED(401, "UNAUTHORIZED"),
  ACCESS_DENIED(403, "ACCESS_DENIED"),
  SCHEMA_VALIDATION_ERROR(400, "SCHEMA VALIDATION ERROR"),
  INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR"),
  ;

  private final Integer statusCode;
  private final String message;

}
