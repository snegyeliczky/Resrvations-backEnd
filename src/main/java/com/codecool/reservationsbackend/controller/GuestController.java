package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.entity.Status;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import com.codecool.reservationsbackend.service.EditGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/guest")
public class GuestController {


    @Autowired
    private EditGuest editGuest;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/all")
    public List<Guest> allGuest() {
        return guestRepository.findAll();
    }

    @GetMapping("/checkin")
    public List<Guest> checkInList(
            @RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestRepository.findByCheckInEquals(LocalDate.now());
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestRepository.findByCheckInEquals(localDate);
    }

    @GetMapping("/in")
    public List<Guest> inList(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestRepository.findGuestsByStatus(Status.IN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestRepository.findGuestsByStatusAndCheckInIsLike(Status.IN, localDate);
    }

    @GetMapping("/checkout")
    public List<Guest> outListByDate(@RequestParam(value = "date", required = false) String date) {
        if (StringUtils.isEmpty(date)) {
            return guestRepository.findGuestsByStatus(Status.CHECKIN);
        }
        LocalDate localDate = LocalDate.parse(date);
        return guestRepository.findGuestsByStatusAndCheckInIsLike(Status.CHECKOUT, localDate);
    }



    @PutMapping("/changestatus")
    public void changeGuestStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") String status, @RequestBody
            HashMap map) {
        if (StringUtils.isEmpty(status)){
            return;
        }
        System.out.println(map.toString());
        System.out.println(id+status);
        guestRepository.updateStatus(Status.valueOf(status), Long.parseLong(id));
        return;
    }

    /*
    @GetMapping("/search/{id}")
    public List<Guest> getGuest(@PathVariable Long id) {
        return guestStorage.getGuestListByGuestId(id);
    }
     */
    @PutMapping("/setroom")
    public Guest setRoom(@RequestParam(value = "roomId") String roomId, @RequestParam(value = "guestId") String guestId) {
        Room room = null;

        if (roomRepository.findAll().stream()
                .anyMatch(room1 -> room1.getId().equals(Long.parseLong(roomId)))) {
            room = roomRepository.getOne(Long.parseLong(roomId));
        }
        guestRepository.updateGuestRoom(room, Long.parseLong(guestId));

        return guestRepository.getOne(Long.parseLong(guestId));
    }

    @PutMapping("/edit")
    public void e1ditGuest(@RequestBody Guest guest) { editGuest.editGuest(guest); }
}
