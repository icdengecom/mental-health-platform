package com.icdenge.service.user;

import com.icdenge.dto.request.SignUpRequest;
import com.icdenge.mapper.UserMapper;
import com.icdenge.persistence.entity.User;
import com.icdenge.persistence.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

  private final UserMapper mapper;
  private final UserRepository repository;

  @Override
  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public User signup(SignUpRequest request) {
    if (existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Username or email already exists.");
    }
    User user = mapper.convert(request);
    return repository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
  }
}
