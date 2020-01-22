package com.codecool.reservationsbackend.controller;


import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.service.GuestCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/add")
public class AddController {

    @Autowired
    private GuestCreator guestCreator;

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/guest")
    public Guest createRandomGuest() {
        Guest guest = guestCreator.createRandomGuest();
        guestRepository.save(guest);
        return guest;
    }

}
