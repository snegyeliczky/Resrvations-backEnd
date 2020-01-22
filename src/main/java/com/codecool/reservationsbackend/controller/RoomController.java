package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/list")
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
