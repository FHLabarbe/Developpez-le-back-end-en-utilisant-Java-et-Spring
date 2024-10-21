package com.openclassrooms.controllers;

import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.RentalsDTO;
import com.openclassrooms.services.JwtService;
import com.openclassrooms.services.RentalsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rentals API", description = "API pour gérer les locations")
public class RentalsController {

  @Autowired
  private RentalsServiceImpl rentalsService;

  @Autowired
  private JwtService jwtService;

  @Operation(summary = "Récupérer toutes les locations", description = "Retourne la liste complète des locations disponibles")
  @ApiResponse(responseCode = "200", description = "Liste des locations retournée avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rentals.class)))
  @GetMapping
  public ResponseEntity<Iterable<Rentals>> getAllRentals() {
    Iterable<Rentals> rentals = rentalsService.getAllRentals();
    return new ResponseEntity<>(rentals, HttpStatus.OK);
  }

  @Operation(summary = "Récupérer une location par son ID", description = "Retourne les détails d'une location à partir de son identifiant")
  @ApiResponse(responseCode = "200", description = "Location retournée avec succès",
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rentals.class)))
  @ApiResponse(responseCode = "404", description = "Location non trouvée")
  @GetMapping("/{id}")
  public ResponseEntity<Rentals> getRentalById(@Parameter(description = "ID de la location à récupérer", example = "1")@PathVariable Long id){
    Optional<Rentals> rental = rentalsService.getRentalById(id);
    if (rental.isPresent()) {
      return new ResponseEntity<>(rental.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Mettre à jour une location", description = "Mise à jour des détails d'une location existante")
  @ApiResponse(responseCode = "200", description = "Location mise à jour avec succès")
  @ApiResponse(responseCode = "404", description = "Location non trouvée")
  @PutMapping("/{id}")
  public ResponseEntity<String> updateRental(@Parameter(description = "ID de la location à mettre à jour", example = "1") @PathVariable Long id, @RequestBody RentalsDTO rentalsDTO) {
    try {
      Rentals updatedRental = rentalsService.updateRental(id, rentalsDTO);
      return new ResponseEntity<>("Rental updated !", HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("Rental not found", HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Créer une nouvelle location", description = "Crée une nouvelle location en fournissant les détails")
  @ApiResponse(responseCode = "200", description = "Location créée avec succès")
  @ApiResponse(responseCode = "400", description = "Requête invalide ou données manquantes")
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> createRental(@RequestParam("name") String name,
                                             @RequestParam("surface") int surface,
                                             @RequestParam("price") int price,
                                             @RequestParam("description") String description,
                                             @RequestParam("picture") MultipartFile picture,
                                             @RequestHeader("Authorization") String authorizationHeader) {
    String token = null;
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      token = authorizationHeader.substring(7);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token manquant ou invalide");
    }
    RentalsDTO rentalsDTO = new RentalsDTO();
    rentalsDTO.setName(name);
    rentalsDTO.setSurface(surface);
    rentalsDTO.setPrice(price);
    rentalsDTO.setDescription(description);
    Rentals newRental = rentalsService.createRental(rentalsDTO, picture, token);
    return ResponseEntity.ok("Rental created with ID: " + newRental.getId());
  }
}
