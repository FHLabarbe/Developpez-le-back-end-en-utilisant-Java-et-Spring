package com.openclassrooms.repository;

import com.openclassrooms.model.Rentals;
import org.springframework.data.repository.CrudRepository;

public interface RentalsRepository extends CrudRepository <Rentals,Long> {

}
