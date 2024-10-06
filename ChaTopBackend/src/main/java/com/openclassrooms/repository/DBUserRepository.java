package com.openclassrooms.repository;

import com.openclassrooms.model.DBUser;
import org.springframework.data.repository.CrudRepository;

public interface DBUserRepository extends CrudRepository<DBUser, Integer> {
  DBUser findByEmail(String email);
}
