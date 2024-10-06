package com.openclassrooms.model;

import com.openclassrooms.validation.OnRegister;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDTO{
  private Integer id;
  @NonNull
  private String email;
  @NotNull(groups=OnRegister.class)
  private String name;
  @NonNull
  private String password;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
