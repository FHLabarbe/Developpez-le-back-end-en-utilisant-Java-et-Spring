package com.openclassrooms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Données de la location (DTO) à utiliser pour la création ou la mise à jour")
public class RentalsDTO {

  @Schema(description = "Nom de la location", example = "Appartement en centre-ville", required = true)
  private String name;

  @Schema(description = "Surface de la location en mètres carrés", example = "100", required = true)
  private int surface;

  @Schema(description = "Prix mensuel de la location en euros", example = "1200", required = true)
  private int price;

  @Schema(description = "URL ou chemin de l'image de la location", example = "/images/appartement.jpg")
  private String picture;

  @Schema(description = "Description détaillée de la location", example = "Un grand appartement lumineux en centre-ville")
  private String description;

  @Schema(description = "ID du propriétaire de la location", example = "1", required = true)
  private Long owner_id;
}
