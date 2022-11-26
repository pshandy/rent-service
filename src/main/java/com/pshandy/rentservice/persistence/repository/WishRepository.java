package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.Wish;
import org.springframework.data.repository.CrudRepository;

public interface WishRepository extends CrudRepository<Wish, Integer> {
}
