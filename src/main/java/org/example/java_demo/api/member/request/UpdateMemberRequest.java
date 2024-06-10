package org.example.java_demo.api.member.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateMemberRequest(
    String name,
    String password,
    String regNo
) {

  public void validate() {

  }
}