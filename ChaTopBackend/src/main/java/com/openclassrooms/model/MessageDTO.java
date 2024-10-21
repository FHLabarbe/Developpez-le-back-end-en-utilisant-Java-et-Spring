package com.openclassrooms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Données du message (DTO) à utiliser pour la création d'un message")
public class MessageDTO {

  @Schema(description = "Contenu du message", example = "Je suis intéressé par cette location.", required = true)
  private String message;

  @Schema(description = "ID de l'utilisateur qui envoie le message", example = "1", required = true)
  private Long user_id;

  @Schema(description = "ID de la location à laquelle le message est lié", example = "1", required = true)
  private Long rental_id;
}
