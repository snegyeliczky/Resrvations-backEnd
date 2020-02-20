package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import com.codecool.reservationsbackend.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rooms")
@Slf4j
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/get-available-room")
    public List<Room> getAvailableRoomsByDates(@RequestParam(value = "checkin") String checkIn,
                                               @RequestParam(value = "checkout", required = false) String checkOut) {
        LocalDate checkInDate = LocalDate.parse(checkIn);
        if (StringUtils.isEmpty(checkOut)){
            return reservationService.getAvailableRoomsByDates(checkInDate, checkInDate.plusDays(1));

        }else {
            LocalDate checkOutDate = LocalDate.parse(checkOut);
            return reservationService.getAvailableRoomsByDates(checkInDate, checkOutDate);
        }
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
