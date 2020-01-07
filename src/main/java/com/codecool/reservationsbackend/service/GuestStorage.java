package com.codecool.reservationsbackend.service;


import com.codecool.reservationsbackend.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GuestStorage {

    private List<Guest> guestList = new LinkedList<>();

    @Autowired
    private GuestCreator guestCreator;

    public void addGuestToList(Guest guest) {
        this.guestList.add(guest);
    }

    public List<Guest> getGuestList() {
        return guestList;
    }
}
