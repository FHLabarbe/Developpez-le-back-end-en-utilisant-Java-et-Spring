package com.openclassrooms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="RENTALS")
public class Rentals {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  @Column(name="name")
  private String name;
  @Column(name="surface")
  private int surface;
  @Column(name="price")
  private int price;
  @Column(name="picture")
  private String picture;
  @Column(name="description")
  private String description;
  @Column(name="owner_id")
  private Long ownerId;
  @Column(name="created_at")
  private LocalDateTime createdAt;
  @Column(name="updated_at")
  private LocalDateTime updatedAt;

}
