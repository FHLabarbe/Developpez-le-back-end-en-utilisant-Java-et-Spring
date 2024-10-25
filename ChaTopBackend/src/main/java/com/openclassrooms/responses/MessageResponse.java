package com.openclassrooms.responses;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Réponse après la création d'un message")
public class MessageResponse {

  @Schema(description = "Message de réponse", example = "Message send with success")
  private String message;
}
