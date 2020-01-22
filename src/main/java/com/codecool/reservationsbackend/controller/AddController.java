package com.codecool.reservationsbackend.controller;


import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.service.GuestCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/add")
public class AddController {

    @Autowired
    private GuestCreator guestCreator;

    @PostMapping("/guest")
    public void addGuest(@RequestBody Guest guest) {
        guestCreator.addGuest(guest);
    }


}
