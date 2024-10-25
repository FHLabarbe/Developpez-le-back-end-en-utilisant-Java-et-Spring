package com.openclassrooms.controllers;


import com.openclassrooms.model.MessageDTO;
import com.openclassrooms.model.MessageResponse;
import com.openclassrooms.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages API", description = "API pour gérer les messages")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @Operation(summary = "Créer un nouveau message", description = "Crée un message en fournissant l'ID utilisateur et l'ID de la location associée.")
  @ApiResponse(responseCode = "201", description = "Message créé avec succès",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)))
  @ApiResponse(responseCode = "400", description = "Requête invalide")
  @PostMapping
  public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageDTO messageDTO) {
    try {
      messageService.createMessage(messageDTO);
      MessageResponse response = new MessageResponse("Message send with success");
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
