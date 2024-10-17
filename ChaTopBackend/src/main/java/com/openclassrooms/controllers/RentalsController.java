package com.openclassrooms.controllers;

import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.RentalsDTO;
import com.openclassrooms.services.RentalsServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/rentals")
@RestController
public class RentalsController {

  @Autowired
  private RentalsServiceImpl rentalsService;

  @GetMapping
  public ResponseEntity<Iterable<Rentals>> getAllRentals() {
    Iterable<Rentals> rentals = rentalsService.getAllRentals();
    return new ResponseEntity<>(rentals, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Rentals> getRentalById(@PathVariable Long id){
    Optional<Rentals> rental = rentalsService.getRentalById(id);
    if (rental.isPresent()) {
      return new ResponseEntity<>(rental.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateRental(@PathVariable Long id, @RequestBody RentalsDTO rentalsDTO) {
    try {
      Rentals updatedRental = rentalsService.updateRental(id, rentalsDTO);
      return new ResponseEntity<>("Rental updated !", HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>("Rental not found", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<String> createRental(@RequestBody RentalsDTO rentalsDTO){
    Rentals newRental = rentalsService.createRental(rentalsDTO);
    return ResponseEntity.ok("Rental created !" + newRental.getId());
  }
}
