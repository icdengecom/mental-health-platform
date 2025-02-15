package com.icdenge.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
  CLIENT("ROLE_CLIENT", "user.role.client"),
  ADMIN("ROLE_ADMIN", "user.role.admin"),
  THERAPIST("ROLE_THERAPIST", "user.role.therapist");

  private final String authority;
  private final String label;
}
