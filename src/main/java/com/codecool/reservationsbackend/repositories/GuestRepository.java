package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.GuestDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GuestRepository extends JpaRepository<GuestDB, Long> {

    List<GuestDB> findByCheckInEquals(LocalDate date);
}
