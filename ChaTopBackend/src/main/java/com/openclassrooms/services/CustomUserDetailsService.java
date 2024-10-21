package com.openclassrooms.services;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private DBUserRepository dbUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    DBUser user = dbUserRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with email: " + username);
    }
    return new UserDetailsImpl(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getUpdated_at(),user.getCreated_at());
  }
}
