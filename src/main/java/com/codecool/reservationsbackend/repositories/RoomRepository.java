package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
