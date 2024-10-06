package com.openclassrooms.services;

import com.openclassrooms.model.UserDTO;

public interface AuthenticationService {
  void register(UserDTO userDTO);
  String login(UserDTO userDTO);
}
