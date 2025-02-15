package com.icdenge.common.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SystemEntity {

  USER("User"),
  COMMENT("Comment");

  private final String name;
}
