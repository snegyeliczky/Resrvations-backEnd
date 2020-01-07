package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.model.Status;
import com.codecool.reservationsbackend.service.GuestStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestStorage guestStorage;

    @GetMapping("/checkin")
    @ResponseBody
    public List<Guest> checkInList(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatus(Status.CHECKIN, localDate);
    }

    @GetMapping("/in")
    @ResponseBody
    public List<Guest> inList(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatus(Status.IN, localDate);
    }

    @GetMapping("/checkout")
    @ResponseBody
    public List<Guest> checkoutList(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatus(Status.CHECKOUT, localDate);
    }

    @GetMapping("/out")
    @ResponseBody
    public List<Guest> outList(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatus(Status.OUT, localDate);
    }

}
