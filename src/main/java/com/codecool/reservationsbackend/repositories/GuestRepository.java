package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findByCheckInEquals(LocalDate date);
}
