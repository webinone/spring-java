package org.example.java_demo.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiResponse<T> {

  private static final String SUCCESS = "success";
  private static final String ERROR = "error";

  private final LocalDateTime timestamp = LocalDateTime.now();
  private String status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message;

  public static <T> ApiResponse<T> ok(T data) {
    return ApiResponse.<T>builder()
        .status(SUCCESS)
        .data(data)
        .build();
  }

  public static <T> ApiResponse<T> error(String errorMessage) {
    return ApiResponse.<T>builder()
        .status(ERROR)
        .message(errorMessage)
        .build();
  }
}
