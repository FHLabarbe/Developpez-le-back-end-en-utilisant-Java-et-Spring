package com.openclassrooms.controllers;

import com.openclassrooms.model.UserMeDTO;
import com.openclassrooms.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserServiceImpl userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserMeDTO> getUserById(@PathVariable Long id){
    try {
      UserMeDTO userMeDTO = userService.findUserById(id);
      return ResponseEntity.ok(userMeDTO);
    } catch(HttpClientErrorException.NotFound e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
