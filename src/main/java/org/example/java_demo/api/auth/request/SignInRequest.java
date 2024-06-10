package org.example.java_demo.api.auth.request;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(

    @NotBlank
    String email,
    @NotBlank
    String password
) {

}
