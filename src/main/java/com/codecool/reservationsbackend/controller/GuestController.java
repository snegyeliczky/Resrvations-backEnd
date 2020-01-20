package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Status;
import com.codecool.reservationsbackend.repositories.GuestRepository;
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

    @Autowired
    private GuestRepository guestRepository;


    @GetMapping("/checkin")
    public List<Guest> checkInList(
            @RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestRepository.findAll();
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestRepository.findByCheckInEquals(localDate);
    }

    @GetMapping("/in")
    public List<Guest> inList(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestStorage.getGuestListByStatus(Status.IN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.IN, localDate);
    }

    @GetMapping("/checkout")
    public List<Guest> outListByDate(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestStorage.getGuestListByStatus(Status.CHECKIN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestStorage.getGuestListByStatusAndDate(Status.CHECKOUT, localDate);
    }


    @GetMapping("/changestatus")
    public List<Guest> changeGuestStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") String status) {
        return guestStorage.changeGuestStatus(Long.parseLong(id), Status.valueOf(status));
    }

    @GetMapping("/search/{id}")
    public List<Guest> getGuest(@PathVariable UUID id) {
        return guestStorage.getGuestListByGuestId(id);
    }

    @GetMapping("/setroom")
    public List<Guest> setRoom(@RequestParam(value = "roomId") String roomNumber, @RequestParam(value = "guestId") String guestId) {
        return guestStorage.getGuestList();
    }
}
