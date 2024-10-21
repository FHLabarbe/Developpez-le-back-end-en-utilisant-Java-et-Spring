package com.openclassrooms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="USERS")
@Schema(description = "Entité représentant un utilisateur dans la base de données")
public class DBUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identifiant unique de l'utilisateur", example = "1")
  @Column(name="id")
  private Long id;

  @Schema(description = "Adresse email unique de l'utilisateur", example = "user@example.com", required = true)
  @Column(name="email")
  private String email;

  @Schema(description = "Nom de l'utilisateur", example = "John Doe", required = true)
  @Column(name="name")
  private String name;

  @Schema(description = "Mot de passe chiffré de l'utilisateur", example = "$2a$10$hashDuMotDePasse", required = true)
  @Column(name="password")
  private String password;


  @Schema(description = "Date de création du compte", example = "2024-10-18T14:30:00")
  @Column(name="created_at")
  private LocalDateTime created_at;

  @Schema(description = "Date de dernière mise à jour du compte", example = "2024-10-18T14:30:00")
  @Column(name="updated_at")
  private LocalDateTime updated_at;

}
