package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByEmail(String email);
    User findByEmail(String email);

}
