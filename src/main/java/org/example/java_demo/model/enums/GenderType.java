package org.example.java_demo.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderType {

  MALE("남자"),
  FEMALE("여자");

  private final String description;
}
