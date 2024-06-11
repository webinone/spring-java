package org.example.java_demo.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java_demo.exception.ErrorCode;
import org.example.java_demo.model.api.ApiResponse;
import org.example.java_demo.model.api.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    log.error("Not Autenticated Request", authException);

    ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.UNAUTHORIZED, authException.getMessage());
    var apiResponse = ApiResponse.error(errorResponse);

    String responseBody = objectMapper.writeValueAsString(apiResponse);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(responseBody);

  }
}
