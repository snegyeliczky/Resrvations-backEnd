package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Address;
import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Status;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class GuestCreator {

    private static List<String> names = Arrays.asList("Joco", "Misi", "Béla", "Füles", "Réka", "Ákos", "Ürsiklosi", "Sándi", "Peti", "Megatron");
    @Autowired
    private RandomDateCreator randomDateCreator;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private GuestRepository guestRepository;

    public Guest createRandomGuest(Hotel hotel) {
        Random random = new Random();
        List<LocalDate> dates = randomDateCreator.dateCreator();
        String name = names.get(random.nextInt(names.size()));

        return Guest.builder()
                .name(name)
                .checkIn(dates.get(0))
                .checkOut(dates.get(1))
                .status(Status.values()[random.nextInt(Status.values().length)])
                .hotel(hotel)
                .address(Address.builder().email(name + "@gmail.com").build())
                .build();
    }

    public void addGuest(Guest guest, Address address) {
        guest.setAddress(address);
        address.setGuest(guest);
        guest.setStatus(Status.CHECKIN);
        guestRepository.save(guest);
    }
}
