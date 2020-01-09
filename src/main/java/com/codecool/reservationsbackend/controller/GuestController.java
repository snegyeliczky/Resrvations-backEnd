package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.model.Status;
import com.codecool.reservationsbackend.service.GuestStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestStorage guestStorage;


    @GetMapping("/checkin")
    public List<Guest> checkInList(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)){
        return guestStorage.getGuestListByStatus(Status.CHECKIN);
    }
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.CHECKIN, localDate);
    }

    @GetMapping("/in")
    public List<Guest> inList() {
        return guestStorage.getGuestListByStatus(Status.IN);
    }

    @GetMapping("/checkout")
    public List<Guest> checkoutList() {
        return guestStorage.getGuestListByStatus(Status.CHECKOUT);
    }

    @GetMapping("/checkout/{date}")
    public List<Guest> outListByDate(@PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.CHECKOUT,localDate);
    }

    @GetMapping("/out")
    public List<Guest> outList() {
        return guestStorage.getGuestListByStatus(Status.OUT);
    }

    @PutMapping("/changestatus/{id}/{status}")
    public List<Guest> changeGuestStatus(@PathVariable UUID id, @PathVariable Status status) {
        return guestStorage.changeGuestStatus(id, status);
    }
}
