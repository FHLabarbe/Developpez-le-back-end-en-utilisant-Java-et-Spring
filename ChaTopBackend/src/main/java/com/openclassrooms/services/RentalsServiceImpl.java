package com.openclassrooms.services;

import com.openclassrooms.model.DBUser;
import com.openclassrooms.model.RentalUpdateDTO;
import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.RentalsDTO;
import com.openclassrooms.repository.DBUserRepository;
import com.openclassrooms.repository.RentalsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RentalsServiceImpl implements RentalsService {

  @Autowired
  private RentalsRepository rentalsRepository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private DBUserRepository dbUserRepository;

  public Iterable<Rentals> getAllRentals(){
    return rentalsRepository.findAll();
  }

  public Optional<Rentals> getRentalById(Long id){
    return rentalsRepository.findById(id);
  }

  @Override
  public Rentals createRental(RentalsDTO rentalsDTO, MultipartFile picture, String token) {
    String uploadDir = "/Users/fh.lab/Documents/Formation OCR/Developpeur Full-Stack - Java et Angular/Projet 3 - Développez le back-end en utilisant Java et Spring/images";
    String fileName = System.currentTimeMillis() + "_" + picture.getOriginalFilename();
    Path filePath = Paths.get(uploadDir, fileName);
    try {
      Files.write(filePath, picture.getBytes());
    } catch (IOException exception) {
      throw new RuntimeException("Error occurred while saving the file: " + exception.getMessage());
    }
    String email = jwtService.decodeToken(token);
    if (email == null) {
      throw new RuntimeException("Invalid token");
    }
    DBUser dbUser = dbUserRepository.findByEmail(email);
    if (dbUser == null) {
      throw new RuntimeException("User not found with email: " + email);
    }
    Rentals rental = new Rentals();
    rental.setName(rentalsDTO.getName());
    rental.setSurface(rentalsDTO.getSurface());
    rental.setPrice(rentalsDTO.getPrice());
    rental.setPicture(fileName);
    rental.setDescription(rentalsDTO.getDescription());
    rental.setOwner_id(dbUser.getId());
    rental.setCreated_at(LocalDateTime.now());
    rental.setUpdated_at(LocalDateTime.now());
    return rentalsRepository.save(rental);
  }

  @Override
  public Rentals updateRental(Long id, RentalUpdateDTO rentalUpdateDTO) {
    Optional<Rentals> optionalRental = rentalsRepository.findById(id);
    if (optionalRental.isPresent()) {
      Rentals rental = optionalRental.get();
      rental.setName(rentalUpdateDTO.getName());
      rental.setSurface(rentalUpdateDTO.getSurface());
      rental.setPrice(rentalUpdateDTO.getPrice());
      rental.setDescription(rentalUpdateDTO.getDescription());
      rental.setUpdated_at(LocalDateTime.now());
      return rentalsRepository.save(rental);
    } else {
      throw new EntityNotFoundException("Rental not found with ID: " + id);
    }
  }
}
