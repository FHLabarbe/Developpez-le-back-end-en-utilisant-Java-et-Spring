package com.openclassrooms.services;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.model.UserMeDTO;
import com.openclassrooms.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private DBUserRepository dbUserRepository;

  @Override
  public UserMeDTO findUserById(Long id) {
    Optional<DBUser> optionalDBUser = dbUserRepository.findById(id);
    if(optionalDBUser.isPresent()){
      DBUser user = optionalDBUser.get();
      UserMeDTO userMeDTO = new UserMeDTO(user.getEmail());
      userMeDTO.setId(id);
      userMeDTO.setName(user.getName());
      userMeDTO.setCreated_at(user.getCreated_at());
      userMeDTO.setUpdated_at(user.getUpdated_at());
      return userMeDTO;
    }
    return null;
  }
}
