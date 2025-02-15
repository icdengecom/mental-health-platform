package com.icdenge.config;

import com.icdenge.common.enums.UserRole;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

  public static Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return Optional.of("Unauthorized");
    }

    if ((authentication instanceof AnonymousAuthenticationToken)) {
      return Optional.of("Unauthorized");
    }

    String username = (String) authentication.getPrincipal();
    return Optional.of(username);
  }

  public static String getCurrentAuditorOrElseNull() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      return (String) authentication.getPrincipal();
    }
    return null;
  }

  public static boolean isCurrentUserAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .anyMatch(authority -> UserRole.ADMIN.getAuthority().equals(authority));
  }
}
