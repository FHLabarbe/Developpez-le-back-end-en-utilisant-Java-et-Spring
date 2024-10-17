package com.openclassrooms.model;

import com.openclassrooms.validation.OnRegister;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class UserDTO{
  private Integer id;
  @NonNull
  private String email;
  @NotNull(groups=OnRegister.class)
  private String name;
  @NonNull
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
