package com.openclassrooms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="messages")
@Schema(description = "Entité représentant un message associé à une location et à un utilisateur")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identifiant unique du message", example = "1")
  @Column(name="id")
  private Long id;

  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  @Schema(description = "Utilisateur ayant envoyé le message", required = true)
  private DBUser user_id;

  @ManyToOne
  @JoinColumn(name="rental_id", nullable = false)
  @Schema(description = "Location à laquelle le message est lié", required = true)
  private Rentals rental_id;

  @Column(name="message",nullable = false)
  @Schema(description = "Contenu du message", example = "Je suis intéressé par cette location.")
  private String message;

  @Column(name="created_at")
  @Schema(description = "Date de création du message", example = "2024-10-18T14:30:00")
  private LocalDateTime created_at;

  @Column(name="updated_at")
  @Schema(description = "Date de dernière mise à jour du message", example = "2024-10-18T14:30:00")
  private LocalDateTime updated_at;
}
