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
    public List<Guest> inList(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)){
        return guestStorage.getGuestListByStatus(Status.IN);
    }
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.IN, localDate);
    }


    @GetMapping("/checkout")
    public List<Guest> outListByDate(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)){
            return guestStorage.getGuestListByStatus(Status.CHECKIN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.CHECKOUT,localDate);
    }

    @GetMapping("/out")
    public List<Guest> outList(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)){
            return guestStorage.getGuestListByStatus(Status.OUT);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.OUT,localDate);
    }

    @PutMapping("/changestatus/{id}/{status}")
    public List<Guest> changeGuestStatus(@PathVariable UUID id, @PathVariable Status status) {
        return guestStorage.changeGuestStatus(id, status);
    }

    @GetMapping("/search/{id}")
    public List<Guest> getGuest(@PathVariable UUID id) {
        return guestStorage.getGuestListByGuestId(id);
    }

    @PostMapping("/setroom")
    public List<Guest> setRoom() {
        return null;
    }
}
