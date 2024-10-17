package com.openclassrooms.services;

import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.RentalsDTO;
import com.openclassrooms.repository.RentalsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RentalsServiceImpl implements RentalsService {

  @Autowired
  private RentalsRepository rentalsRepository;

  public Iterable<Rentals> getAllRentals(){
    return rentalsRepository.findAll();
  }

  public Optional<Rentals> getRentalById(Long id){
    return rentalsRepository.findById(id);
  }

  @Override
  public Rentals createRental(RentalsDTO rentalsDTO) {
    Rentals rental = new Rentals();
    rental.setName(rentalsDTO.getName());
    rental.setSurface(rentalsDTO.getSurface());
    rental.setPrice(rentalsDTO.getPrice());
    rental.setPicture(rentalsDTO.getPicture());
    rental.setDescription(rentalsDTO.getDescription());
    rental.setOwnerId(rentalsDTO.getOwnerId());
    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());
    return rentalsRepository.save(rental);
  }

  @Override
  public Rentals updateRental(Long id, RentalsDTO rentalsDTO) {
    Optional<Rentals> optionalRental = rentalsRepository.findById(id);
    if (optionalRental.isPresent()) {
      Rentals rental = optionalRental.get();
      rental.setName(rentalsDTO.getName());
      rental.setSurface(rentalsDTO.getSurface());
      rental.setPrice(rentalsDTO.getPrice());
      rental.setPicture(rentalsDTO.getPicture());
      rental.setDescription(rentalsDTO.getDescription());
      rental.setOwnerId(rentalsDTO.getOwnerId());
      rental.setUpdatedAt(LocalDateTime.now());
      return rentalsRepository.save(rental);
    } else {
      throw new EntityNotFoundException("Rental not found with ID: " + id);
    }
  }
}
