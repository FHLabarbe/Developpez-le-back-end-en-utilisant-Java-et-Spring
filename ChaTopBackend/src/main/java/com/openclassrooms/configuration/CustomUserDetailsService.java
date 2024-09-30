package com.openclassrooms.configuration;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private DBUserRepository dbUserRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    DBUser user = dbUserRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }
    return User.builder()
      .username(user.getEmail())
      .password(user.getPassword())
      .authorities(Collections.emptyList())
      .build();
  }
}
