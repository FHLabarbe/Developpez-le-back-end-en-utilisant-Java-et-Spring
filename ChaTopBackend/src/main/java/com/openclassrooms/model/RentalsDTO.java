package com.openclassrooms.model;

import lombok.Data;

@Data
public class RentalsDTO {
  private String name;
  private int surface;
  private int price;
  private String picture;
  private String description;
  private Long ownerId;
}
