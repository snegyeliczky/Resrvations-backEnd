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


    @GetMapping("/changestatus")
    public List<Guest> changeGuestStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") String status) {
        return guestStorage.changeGuestStatus(UUID.fromString(id), Status.valueOf(status));
    }
}
