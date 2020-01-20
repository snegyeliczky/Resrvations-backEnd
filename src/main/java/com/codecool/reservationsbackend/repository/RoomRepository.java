package com.codecool.reservationsbackend.repository;

import com.codecool.reservationsbackend.entity.RoomDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomDB, Long> {
}
