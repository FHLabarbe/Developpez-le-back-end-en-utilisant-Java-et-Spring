package com.openclassrooms.model;

import lombok.Data;

@Data
public class RentalsListDTO {
  private Iterable<Rentals> rentals;

  public RentalsListDTO(Iterable<Rentals> rentals){
    this.rentals = rentals;
  }
}
