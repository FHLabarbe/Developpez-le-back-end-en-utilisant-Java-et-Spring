package com.openclassrooms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="RENTALS")
@Schema(description = "Entité représentant une location dans la base de données")
public class Rentals {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identifiant unique de la location", example = "1")
  @Column(name="id")
  private Long id;

  @Schema(description = "Nom de la location", example = "Appartement en centre-ville")
  @Column(name="name")
  private String name;

  @Schema(description = "Surface de la location en mètres carrés", example = "100")
  @Column(name="surface")
  private int surface;

  @Schema(description = "Prix mensuel de la location en euros", example = "1200")
  @Column(name="price")
  private int price;

  @Schema(description = "URL ou chemin de l'image de la location", example = "/images/appartement.jpg")
  @Column(name="picture")
  private String picture;

  @Schema(description = "Description détaillée de la location", example = "Un grand appartement lumineux avec terrasse")
  @Column(name="description")
  private String description;

  @Schema(description = "ID du propriétaire de la location", example = "1")
  @Column(name="owner_id")
  private Long owner_id;

  @Schema(description = "Date de création de la location", example = "2024-10-18T14:30:00")
  @Column(name="created_at")
  private LocalDateTime created_at;

  @Schema(description = "Date de la dernière mise à jour de la location", example = "2024-10-18T14:30:00")
  @Column(name="updated_at")
  private LocalDateTime updated_at;

}
