package com.openclassrooms.repository;

import com.openclassrooms.model.DBUser;
import org.springframework.data.repository.CrudRepository;

public interface DBUserRepository extends CrudRepository<DBUser, Long> {
  DBUser findByEmail(String email);
}
