package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.repositories.GuestRepository;
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

    @GetMapping("/")
    public List<Guest> mainPage() {
        return guestRepository.findAll();
    }
}
