package com.icdenge.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum UserRole implements GrantedAuthority {

  CLIENT("ROLE_CLIENT", "user.role.client"),
  ADMIN("ROLE_ADMIN", "user.role.admin"),
  THERAPIST("ROLE_THERAPIST", "user.role.therapist");

  private final String authority;
  private final String label;
  
}
