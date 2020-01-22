package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RouteController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/")
    public List<Hotel> mainPage() {
        return hotelRepository.findAll();
    }
}
