package com.openclassrooms.controllers;

import com.openclassrooms.model.UserDTO;
import com.openclassrooms.model.UserMeDTO;
import com.openclassrooms.repository.DBUserRepository;
import com.openclassrooms.responses.TokenResponse;
import com.openclassrooms.services.AuthenticationServiceImpl;
import com.openclassrooms.services.CustomUserDetailsService;
import com.openclassrooms.services.JwtService;
import com.openclassrooms.validation.OnRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

  @Autowired
  AuthenticationServiceImpl authenticationService;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private DBUserRepository dbUserRepository;
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@Validated(OnRegister.class) @RequestBody UserDTO userDTO) {
    try {
      authenticationService.register(userDTO);
      return ResponseEntity.ok(new TokenResponse(jwtService.generateToken(userDTO)));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
    try {
      String token = authenticationService.login(userDTO);
      return ResponseEntity.ok(new TokenResponse(token));
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/me")
  public ResponseEntity<UserMeDTO> getCurrentUser(Principal principal) {
    try {
      UserMeDTO userMeDTO = authenticationService.getCurrentUser(principal);
      return ResponseEntity.ok(userMeDTO);
    } catch (AccessDeniedException e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
  }


}
