package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.Premise;
import org.springframework.data.repository.CrudRepository;

public interface PremiseRepository extends CrudRepository<Premise, Integer> {
}
