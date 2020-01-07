package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.service.GuestStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RouteController {

    @Autowired
    private GuestStorage guestStorage;

    @GetMapping("/")
    public List<Guest> mainPage() {
        return guestStorage.getGuestList();
    }
}
