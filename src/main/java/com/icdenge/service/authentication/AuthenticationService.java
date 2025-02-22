package com.icdenge.service.authentication;

import com.icdenge.dto.request.LoginRequest;
import com.icdenge.dto.request.SignUpRequest;
import com.icdenge.dto.response.TokenResponse;

public interface AuthenticationService {
  TokenResponse signup(SignUpRequest request);
  TokenResponse login(LoginRequest request);
}
