package com.openclassrooms.controllers;

import com.openclassrooms.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {



  @GetMapping("/user")
  public String getUser() {
    return "Welcome, User";
  }

  @GetMapping("/admin")
  public String getAdmin() {
    return "Welcome, Admin";
  }

}
