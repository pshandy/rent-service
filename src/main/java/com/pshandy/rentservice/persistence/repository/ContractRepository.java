package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
}
