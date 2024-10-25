package com.openclassrooms.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Réponse après la mise à jour d'une location")
public class UpdateRentalResponse {

  @Schema(description = "Message de réponse", example = "Rental updated !")
  private String message;
}
