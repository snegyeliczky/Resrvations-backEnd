package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {


    List<Guest> findByCheckInEquals(LocalDate date);

    List<Guest> findGuestsByStatus(Status status);

    List<Guest> findGuestsByStatusAndCheckInIsLike(Status status, LocalDate checkIn);

    @Query("UPDATE Guest g SET g.status = :status WHERE g.id = :guestId")
    @Modifying(clearAutomatically = true)
    int updateStatus(@Param("status") Status status, @Param("guestId") long guestId);

}
