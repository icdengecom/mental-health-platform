package com.icdenge.service.user;

import com.icdenge.dto.request.SignUpRequest;
import com.icdenge.persistence.entity.User;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User signup(SignUpRequest request);
}
