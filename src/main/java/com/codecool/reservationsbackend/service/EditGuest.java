package com.codecool.reservationsbackend.service;


import com.codecool.reservationsbackend.entity.Address;
import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Status;
import com.codecool.reservationsbackend.repositories.AddressRepository;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class EditGuest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GuestRepository guestRepository;

    public void editGuest(Guest guest) {
        guestRepository.editGuest(guest.getName(), guest.getId());
        addressRepository.editAddress(guest.getAddress().getCountry(),
                guest.getAddress().getCity(),
                guest.getAddress().getStreet(),
                guest.getAddress().getZipCode(),
                guest.getAddress().getEmail(),
                guest.getAddress().getId());
    }
}
