package com.openclassrooms.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private Long id;
  private String email;
  private String name;
  private String password;
  private LocalDateTime updatedAt;
  private LocalDateTime createdAt;

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

}
