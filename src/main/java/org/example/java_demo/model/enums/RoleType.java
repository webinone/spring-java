package org.example.java_demo.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

  ADMIN("관리자"),
  MEMBER("일반회원"),
  GUEST("게스트");

  private final String descrption;

}
