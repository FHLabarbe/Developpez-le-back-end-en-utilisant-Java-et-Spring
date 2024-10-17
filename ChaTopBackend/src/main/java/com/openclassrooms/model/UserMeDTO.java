package com.openclassrooms.model;

import com.openclassrooms.validation.OnRegister;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class UserMeDTO{
  private Long id;
  @NonNull
  private String email;
  @NotNull(groups=OnRegister.class)
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
