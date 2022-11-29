package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.Request;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Integer> {
}
