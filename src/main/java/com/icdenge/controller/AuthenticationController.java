package com.icdenge.controller;

import com.icdenge.dto.request.LoginRequest;
import com.icdenge.dto.request.SignUpRequest;
import com.icdenge.dto.response.TokenResponse;
import com.icdenge.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  public ResponseEntity<TokenResponse> signup(@RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authenticationService.signup(signUpRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok(authenticationService.login(loginRequest));
  }
}
