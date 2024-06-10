package org.example.java_demo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.example.java_demo.model.enums.RoleType;

@Getter
@Builder
public class Member {

  private Long id;

  private String email;

  @JsonIgnore
  private String password;

  private String regNo;

  private RoleType role;

  public static Member of(final Long id, final String email, final String password, final String regNo, final RoleType role) {
    return Member.builder()
        .id(id)
        .email(email)
        .password(password)
        .regNo(regNo)
        .role(role)
        .build();
  }

}
