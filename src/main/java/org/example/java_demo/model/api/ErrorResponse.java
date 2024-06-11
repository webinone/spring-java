package org.example.java_demo.model.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.java_demo.exception.ErrorCode;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ErrorResponse {

  private Integer statusCode;
  private String message;

  public ErrorResponse(Integer statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public static ErrorResponse of(ErrorCode errorCode) {;
    return of(errorCode, errorCode.getMessage());
  }

  public static ErrorResponse of(ErrorCode errorCode, String message) {
    return ErrorResponse.builder()
        .statusCode(errorCode.getStatusCode())
        .message(message)
        .build();
  }
}
