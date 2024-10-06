package com.openclassrooms.controllers;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.model.UserDTO;
import com.openclassrooms.repository.DBUserRepository;
import com.openclassrooms.responses.TokenResponse;
import com.openclassrooms.services.*;
import com.openclassrooms.validation.OnRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<UserDetailsImpl> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    String email = authentication.getName();
    UserDetailsImpl userDetails = (UserDetailsImpl) customUserDetailsService.loadUserByUsername(email);

    return ResponseEntity.ok(userDetails);
  }

}
