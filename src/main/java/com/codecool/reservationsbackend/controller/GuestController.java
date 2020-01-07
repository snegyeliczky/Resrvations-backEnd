package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.model.Status;
import com.codecool.reservationsbackend.service.GuestStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestStorage guestStorage;

    @GetMapping("/checkin")
    public List<Guest> checkInList() {
        return guestStorage.getGuestListByStatus(Status.CHECKIN);
    }

    @GetMapping("/in")
    public List<Guest> inList() {
        return guestStorage.getGuestListByStatus(Status.IN);
    }

    @GetMapping("/checkout")
    public List<Guest> checkoutList() {
        return guestStorage.getGuestListByStatus(Status.CHECKOUT);
    }

    @GetMapping("/out")
    public List<Guest> outList() {
        return guestStorage.getGuestListByStatus(Status.OUT);
    }

}
