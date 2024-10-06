package com.openclassrooms.services;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.model.UserDTO;
import com.openclassrooms.repository.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private DBUserRepository dbUserRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private JwtService jwtService;

  @Override
  public void register(UserDTO userDTO) {
    userDTO.setCreatedAt(LocalDate.now());
    userDTO.setUpdatedAt(LocalDate.now());
    userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    DBUser dbUser = modelMapper.map(userDTO,DBUser.class);
    dbUserRepository.save(dbUser);
  }

  @Override
  public String login(UserDTO userDTO){
    if (userDTO.getEmail() != null){
      DBUser dbUser = dbUserRepository.findByEmail(userDTO.getEmail());
      if (bCryptPasswordEncoder.matches(userDTO.getPassword(),dbUser.getPassword())){
        return jwtService.generateToken(userDTO);
      }
        throw new RuntimeException("Invalid Credentials");
    }
    throw new RuntimeException("User not found");
  }

}
