package org.example.java_demo.api.member.request;

import jakarta.validation.constraints.NotBlank;

public record CreateMemberRequest(

    @NotBlank
    String name,
    @NotBlank
    String password,
    @NotBlank
    String regNo
) {

  public void validate() {

  }
}

