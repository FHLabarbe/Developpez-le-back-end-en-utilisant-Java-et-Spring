package com.openclassrooms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="USERS")
public class DBUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;
  @Column(name="email")
  private String email;
  @Column(name="name")
  private String name;
  @Column(name="password")
  private String password;
  @Column(name="created_at")
  private LocalDateTime createdAt;
  @Column(name="updated_at")
  private LocalDateTime updatedAt;

}
