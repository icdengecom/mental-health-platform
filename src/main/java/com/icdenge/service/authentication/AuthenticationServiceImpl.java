package com.icdenge.service.authentication;

import com.icdenge.config.security.jwt.JwtTokenUtil;
import com.icdenge.dto.request.LoginRequest;
import com.icdenge.dto.request.SignUpRequest;
import com.icdenge.dto.response.TokenResponse;
import com.icdenge.persistence.entity.User;
import com.icdenge.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  public TokenResponse signup(SignUpRequest request) {
    SignUpRequest modifiedRequest =
        request.toBuilder().password(passwordEncoder.encode(request.getPassword())).build();
    User signup = userService.signup(modifiedRequest);
    return authenticate(request.getEmail(), request.getPassword());
  }

  @Override
  public TokenResponse login(LoginRequest request) {
    return authenticate(request.getEmail(), request.getPassword());
  }

  private TokenResponse authenticate(String email, String password) {
    var authToken = new UsernamePasswordAuthenticationToken(email, password);
    Authentication auth = authenticationManager.authenticate(authToken);
    return new TokenResponse(jwtTokenUtil.generateToken(auth));
  }
}
