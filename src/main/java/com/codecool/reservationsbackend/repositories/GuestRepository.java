package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Transactional
public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findByCheckInEquals(LocalDate date);

    List<Guest> findGuestsByStatus(Status status);

    List<Guest> findGuestsByStatusAndCheckInIsLike(Status status, LocalDate checkIn);

    @Query("UPDATE Guest g SET g.status = :status WHERE g.id = :guestId")
    @Modifying(clearAutomatically = true)
    void updateStatus(@Param("status") Status status, @Param("guestId") long guestId);

}
