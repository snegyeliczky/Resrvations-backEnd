package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Reservation;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.entity.Status;
import com.codecool.reservationsbackend.repositories.ReservationRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import com.codecool.reservationsbackend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    @GetMapping("/get-all")
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @PostMapping("/add-reservation")
    public void addReservation(@RequestBody Reservation reservation) {
        reservationService.addNewReservation(reservation);
    }

    @GetMapping("/get-reservation")
    public Reservation getReservationById(@RequestParam(value = "reservationId") String reservationId) {
        return reservationRepository.getOne(Long.parseLong(reservationId));
    }

    @GetMapping("/checkin")
    public List<Reservation> checkInList(
            @RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return reservationRepository.findByCheckInEquals(LocalDate.now());
        }
        LocalDate localDate = LocalDate.parse(date);
        return reservationRepository.findByCheckInEquals(localDate);
    }

    @PutMapping("/update")
    public void updateReservation(@RequestBody Reservation reservation) {
        reservationService.updateReservation(reservation);

    }

    /*
    @GetMapping("/in")
    public List<Guest> inList(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestRepository.findGuestsByStatus(Status.IN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestRepository.findGuestsByStatusAndCheckInIsLike(Status.IN, localDate);
    }

    @GetMapping("/checkout")
    public List<Guest> outListByDate(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestRepository.findGuestsByStatus(Status.CHECKIN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestRepository.findGuestsByStatusAndCheckInIsLike(Status.CHECKOUT, localDate);
    }
     */



    @PutMapping("/changestatus")
    public void changeReservationStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") String status) {
        if (StringUtils.isEmpty(status)){
            return;
        }
        reservationRepository.updateStatus(Status.valueOf(status), Long.parseLong(id));
    }

    @PutMapping("/setroom")
    public void setRoom(@RequestParam(value = "roomId") String roomId, @RequestParam(value = "reservationId") String reservationId) {

        if (roomId.equals("-")) {
            reservationRepository.updateRoom(null, Long.parseLong(reservationId));
        }

        if (!roomId.equals("-") &&!roomId.equals("") &&
                roomRepository.findAll().stream().anyMatch(room1 -> room1.getId().equals(Long.parseLong(roomId)))) {
            Room room = roomRepository.getOne(Long.parseLong(roomId));
            reservationRepository.updateRoom(room.getId(), Long.parseLong(reservationId));
        }
    }

    /*
    @PutMapping("/edit")
    public void e1ditGuest(@RequestBody Guest guest) { editGuest.editGuest(guest); }
     */

    @GetMapping("/active-reservations")
    public List<Reservation> activeReservations(@RequestParam(value = "date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return reservationRepository.getUnavailableReservationsByCheckInAndCheckOut(localDate, localDate.plusDays(1));
    }
}
