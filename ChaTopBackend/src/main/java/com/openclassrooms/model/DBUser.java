package com.openclassrooms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name="USERS")
public class DBUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;
  @Column(name="email")
  private String email;
  @Column(name="name")
  private String name;
  @Column(name="password")
  private String password;
  @Column(name="created_at")
  private LocalDate createdAt;
  @Column(name="updated_at")
  private LocalDate updatedAt;

}
