package com.openclassrooms.controllers;

import com.openclassrooms.model.UserDTO;
import com.openclassrooms.model.UserMeDTO;
import com.openclassrooms.repository.DBUserRepository;
import com.openclassrooms.responses.TokenResponse;
import com.openclassrooms.services.AuthenticationServiceImpl;
import com.openclassrooms.services.CustomUserDetailsService;
import com.openclassrooms.services.JwtService;
import com.openclassrooms.validation.OnRegister;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/auth")
@RestController
@Tag(name = "Authentication API", description = "API pour l'authentification et la gestion des utilisateurs")
public class AuthenticationController {

  @Autowired
  AuthenticationServiceImpl authenticationService;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private DBUserRepository dbUserRepository;
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Operation(summary = "Enregistrer un nouvel utilisateur", description = "Permet d'enregistrer un nouvel utilisateur et de générer un token JWT")
  @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
  @ApiResponse(responseCode = "400", description = "Requête invalide")
  @PostMapping("/register")
  public ResponseEntity<?> register(@Validated(OnRegister.class) @Parameter(description = "Données de l'utilisateur à enregistrer") @RequestBody UserDTO userDTO) {
    try {
      authenticationService.register(userDTO);
      return ResponseEntity.ok(new TokenResponse(jwtService.generateToken(userDTO)));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Operation(summary = "Connexion d'un utilisateur", description = "Permet à un utilisateur de se connecter et de recevoir un token JWT")
  @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
  @ApiResponse(responseCode = "401", description = "Identifiants invalides")
  @PostMapping("/login")
  public ResponseEntity<?> login(@Parameter(description = "Données de l'utilisateur pour la connexion") @RequestBody UserDTO userDTO) {
    try {
      String token = authenticationService.login(userDTO);
      return ResponseEntity.ok(new TokenResponse(token));
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }

  @Operation(summary = "Récupérer les informations de l'utilisateur connecté", description = "Retourne les informations de l'utilisateur connecté à partir du token JWT")
  @ApiResponse(responseCode = "200", description = "Informations de l'utilisateur récupérées avec succès",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMeDTO.class)))
  @ApiResponse(responseCode = "403", description = "Accès refusé")
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
