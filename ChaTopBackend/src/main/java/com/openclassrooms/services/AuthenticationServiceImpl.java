package com.openclassrooms.services;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.model.UserDTO;
import com.openclassrooms.model.UserMeDTO;
import com.openclassrooms.repository.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

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
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  public void register(UserDTO userDTO) {
    userDTO.setCreated_at(LocalDateTime.now());
    userDTO.setUpdated_at(LocalDateTime.now());
    userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    DBUser dbUser = modelMapper.map(userDTO,DBUser.class);
    dbUserRepository.save(dbUser);
  }

  @Override
  public String login(UserDTO userDTO){
    DBUser dbUser = dbUserRepository.findByEmail(userDTO.getEmail());
    if (bCryptPasswordEncoder.matches(userDTO.getPassword(),dbUser.getPassword())){
      return jwtService.generateToken(userDTO);
    }
    throw new RuntimeException("Invalid Credentials");
  }

  public UserMeDTO getCurrentUser(Principal principal) {
    if (principal == null) {
      throw new AccessDeniedException("User is not authenticated");
    }

    String email = principal.getName();
    UserDetailsImpl userDetails = (UserDetailsImpl) customUserDetailsService.loadUserByUsername(email);

    UserMeDTO userMeDTO = new UserMeDTO(userDetails.getEmail());
    userMeDTO.setId(userDetails.getId());
    userMeDTO.setName(userDetails.getName());
    userMeDTO.setUpdated_at(userDetails.getUpdatedAt());
    userMeDTO.setCreated_at(userDetails.getCreatedAt());

    return userMeDTO;
  }

}
