package com.icdenge.persistence.entity;

import com.icdenge.config.security.UserRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.Past;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private String name;

  @Column
  private String surname;

  @Column(nullable = false)
  private String password;

  @Past
  @Column(nullable = false)
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRole role;

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(role);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

