package com.openclassrooms.services;

import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.RentalsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface RentalsService {
  public Iterable<Rentals> getAllRentals();
  public Optional<Rentals> getRentalById(Long id);
  public Rentals createRental(RentalsDTO rentalsDTO, MultipartFile picture, String token);
  public Rentals updateRental(Long id, RentalsDTO rentalsDTO);
}
