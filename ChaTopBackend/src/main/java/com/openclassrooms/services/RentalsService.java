package com.openclassrooms.services;

import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.RentalsDTO;

import java.util.Optional;

public interface RentalsService {
  public Iterable<Rentals> getAllRentals();
  public Optional<Rentals> getRentalById(Long id);
  public Rentals createRental(RentalsDTO rentalsDTO);
  public Rentals updateRental(Long id, RentalsDTO rentalsDTO);
}
