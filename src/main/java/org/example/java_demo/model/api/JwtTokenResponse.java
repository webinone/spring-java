package org.example.java_demo.model.api;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record JwtTokenResponse(
    String tokenType,
    String accessToken
) {

  private static final String TOKEN_TYPE = "Bearer";

  public static JwtTokenResponse of (String accessToken) {
    return JwtTokenResponse.builder()
            .tokenType(TOKEN_TYPE)
            .accessToken(accessToken)
            .build();
  }
}
