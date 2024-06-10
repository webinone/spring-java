package org.example.java_demo.api.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.java_demo.model.enums.RoleType;

public record SignUpRequest (
    @NotBlank
    String email,
    @NotBlank
    String password,
    @NotBlank
    String regNo,
    @NotNull
    RoleType role
) {
  public void validate() {

  }
}
