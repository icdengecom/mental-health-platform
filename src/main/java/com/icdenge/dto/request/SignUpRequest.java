package com.icdenge.dto.request;

import com.icdenge.common.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

  @NotBlank(message = "")
  @Size(max = 40, message = "")
  @Email(message = "amannnnnnn")
  private String email;
  @NotBlank(message = "")
  private String username;
  private String name;
  private String surname;

  @NotBlank
  @Size(min = 6, max = 20, message = "")
  private String password;

  private UserRole role;
}
