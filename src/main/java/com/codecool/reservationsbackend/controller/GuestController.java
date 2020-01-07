package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.model.Guest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {



    @PostMapping("/checkin")
    public List<Guest> checkInList() {
        return null;
    }

    @PostMapping("/in")
    public List<Guest> inList() {
        return null;
    }

    @PostMapping("/checkout")
    public List<Guest> checkoutList() {
        return null;
    }

    @PostMapping("/out")
    public List<Guest> outList() {
        return null;
    }

}
