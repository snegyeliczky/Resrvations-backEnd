package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Reservation;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCheckInEquals(LocalDate date);

    @Query("UPDATE Reservation r SET r.status = :status WHERE r.id = :reservationId")
    @Modifying(clearAutomatically = true)
    void updateStatus(@Param("status") Status status, @Param("reservationId") long reservationId);

    @Query("UPDATE Reservation r SET r.roomId = :roomId WHERE r.id = :reservationId")
    @Modifying(clearAutomatically = true)
    int updateRoom(@Param("roomId") Long roomId, @Param("reservationId") long reservationId);


//    @Query("SELECT Reservation FROM Reservation r WHERE (r.checkIn <= :checkIn AND r.checkOut >= :checkIn)" +
//            " OR (r.checkIn < :checkOut AND r.checkOut >= :checkOut)" +
//            " OR (:checkIn <= r.checkIn AND :checkOut >= r.checkIn)")
    @Query("SELECT Reservation FROM Reservation")
    List<Reservation> getUnavailableReservationsByCheckInAndCheckOut(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);




}
