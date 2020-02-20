package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import com.codecool.reservationsbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/get-available-room")
    public List<Room> getAvailableRoomsByDates(@RequestParam("checkin") LocalDate checkIn, @RequestParam("checkout") LocalDate checkOut) {
        return reservationService.getAvailableRoomsByDates(checkIn, checkOut);
    }

    @GetMapping("/get-all")
    public List<Room> roomList() {
        return roomRepository.findAll();
    }

    @GetMapping("/add")
    public Room addRoom() {
        List<Hotel> hotels = hotelRepository.findAll();
        return roomRepository.save(Room.builder()
                .hotel(hotels.get(0))
                .build());
    }
}
