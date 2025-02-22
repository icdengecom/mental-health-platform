package com.icdenge.dto.request;

import com.icdenge.common.utils.validation.email.ValidEmail;
import com.icdenge.config.security.UserRole;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SignUpRequest {

  @ValidEmail private String email;
  private String name;
  private String surname;
  @NotBlank private String password;
  private LocalDate birthDate;
  private UserRole role;
}

