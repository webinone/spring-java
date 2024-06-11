package org.example.java_demo.model.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.example.java_demo.model.enums.RoleType;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class MemberSignInHistory {

  private Long id;

  private String email;

  private String regNo;

  private RoleType role;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  public MemberSignInHistory(Long id, String email, String regNo, RoleType role,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.email = email;
    this.regNo = regNo;
    this.role = role;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
