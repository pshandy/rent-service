package com.pshandy.rentservice.persistence.repository;

import com.pshandy.rentservice.persistence.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}
