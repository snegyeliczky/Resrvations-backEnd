package com.codecool.reservationsbackend.service;


import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.repositories.AddressRepository;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditGuest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GuestRepository guestRepository;

//    public void editGuest(Guest guest) {
//        guestRepository.editGuestById(guest.getFirstName(), guest.getId());
//        addressRepository.editAddressById(guest.getAddress().getCountry(),
//                guest.getAddress().getCity(),
//                guest.getAddress().getStreet(),
//                guest.getAddress().getZipCode(),
//                guest.getAddress().getId());
//    }
}
