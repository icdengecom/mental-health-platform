package com.icdenge.dto.request;

import com.icdenge.common.utils.validation.email.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {

  @ValidEmail
  private String email;
  private String password;
}
