package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
