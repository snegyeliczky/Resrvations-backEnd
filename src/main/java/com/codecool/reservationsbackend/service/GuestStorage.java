package com.codecool.reservationsbackend.service;


import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GuestStorage {

    private List<Guest> guestList = new LinkedList<>();

    public List<Guest> getGuestListByStatus(Status status) {
        return guestList.stream()
                .filter(guest -> guest.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Guest> getGuestListByStatusAndDate(Status status, LocalDate date) {
        switch (status) {
            case CHECKOUT:
                return guestList.stream()
                        .filter(guest -> guest.getCheckOut().equals(date))
                        .collect(Collectors.toList());
            case CHECKIN:
                return guestList.stream()
                        .filter(guest -> guest.getCheckIn().equals(date))
                        .collect(Collectors.toList());
            case IN:
                return guestList.stream()
                        .filter(guest -> guest.getStatus().equals(Status.IN))
                        .filter(guest -> guest.getCheckIn().isBefore(date) && guest.getCheckOut().isAfter(date))
                        .collect(Collectors.toList());
            default:
                return guestList.stream()
                        .filter(guest -> guest.getStatus().equals(status))
                        .collect(Collectors.toList());
        }
    }

    /*

    private Map<Status, Supplier<List<Guest>>> statusFunctions = new HashMap<>();

    public List<Guest> getGuestByStatus(){
        statusFunctions.put(Status.CHECKOUT,()->{
            return guestList.stream()
                    .filter(guest -> guest.getCheckOut().equals(date))
                    .collect(Collectors.toList());
        });
    }




    public List<Guest> getGuestListByStatusAndDate(Status status, LocalDate date) {
        return statusFunctions.get(status).get()
    }
*/

    public void addGuestToList(Guest guest) {
        this.guestList.add(guest);
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public List<Guest> changeGuestStatus(Long id, Status status) {
        for (Guest guest : guestList) {
            if (id.equals(guest.getId())) {
                guest.setStatus(status);
            }
        }
        return guestList;
    }
}
