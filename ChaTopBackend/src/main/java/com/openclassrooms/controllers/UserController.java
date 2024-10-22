package com.openclassrooms.controllers;

import com.openclassrooms.model.UserMeDTO;
import com.openclassrooms.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserServiceImpl userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserMeDTO> getUserById(@PathVariable Long id) {
    System.out.println("Requête reçue pour l'utilisateur avec l'ID : " + id);

    try {
      UserMeDTO userMeDTO = userService.findUserById(id);
      if (userMeDTO == null || userMeDTO.getId() == null) {
        System.out.println("Utilisateur non trouvé avec l'ID : " + id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return ResponseEntity.ok(userMeDTO);
    } catch (Exception e) {
      System.out.println("Erreur lors de la récupération de l'utilisateur avec l'ID : " + id + " | Erreur : " + e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
