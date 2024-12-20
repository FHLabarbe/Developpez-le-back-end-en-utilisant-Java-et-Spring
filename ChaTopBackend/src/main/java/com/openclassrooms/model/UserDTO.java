package com.openclassrooms.model;

import com.openclassrooms.validation.OnRegister;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Schema(description = "Données de l'utilisateur (DTO) utilisées pour l'enregistrement ou la connexion")
public class UserDTO{

  @Schema(description = "Identifiant unique de l'utilisateur", example = "1")
  private Integer id;

  @NonNull
  @Schema(description = "Adresse email de l'utilisateur", example = "user@example.com", required = true)
  private String email;

  @NotNull(groups=OnRegister.class)
  @Schema(description = "Nom de l'utilisateur", example = "John Doe", required = true)
  private String name;

  @NonNull
  @Schema(description = "Mot de passe de l'utilisateur", example = "P@ssw0rd", required = true)
  private String password;

  @Schema(description = "Date de création du compte", example = "2024-10-18T14:30:00")
  private LocalDateTime created_at;

  @Schema(description = "Date de dernière mise à jour du compte", example = "2024-10-18T14:30:00")
  private LocalDateTime updated_at;
}
