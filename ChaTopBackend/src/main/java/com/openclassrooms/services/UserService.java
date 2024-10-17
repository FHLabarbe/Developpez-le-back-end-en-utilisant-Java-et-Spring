package com.openclassrooms.services;

import com.openclassrooms.model.UserMeDTO;

public interface UserService {
  public UserMeDTO findUserById(Long id);
}
