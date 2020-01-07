package com.codecool.reservationsbackend.controller;


import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.service.GuestCreator;
import com.codecool.reservationsbackend.service.GuestStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class addController {

    @Autowired
    private GuestCreator guestCreator;

    @Autowired
    private GuestStorage guestStorage;

    @GetMapping("/guest")
    public Guest createRandomGuest() {
        Guest guest = guestCreator.createRandomGuest();
        guestStorage.addGuestToList(guest);
        return guest;
    }

}
