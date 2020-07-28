//package com.codecool.reservationsbackend.service;
//
//
//import com.codecool.reservationsbackend.entity.Guest;
//import com.codecool.reservationsbackend.repositories.AddressRepository;
//import com.codecool.reservationsbackend.repositories.GuestRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class EditGuest {
//
//    private final AddressRepository addressRepository;
//
//    private final GuestRepository guestRepository;
//
//    public void editGuest(Guest guest) {
//        guestRepository.editGuestById(guest.getFirstName(), guest.getId());
//        addressRepository.editAddressById(guest.getAddress().getCountry(),
//                guest.getAddress().getCity(),
//                guest.getAddress().getStreet(),
//                guest.getAddress().getZipCode(),
//                guest.getAddress().getId());
//    }
//}
