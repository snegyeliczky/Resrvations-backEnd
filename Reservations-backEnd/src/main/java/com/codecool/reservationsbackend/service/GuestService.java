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
import java.util.UUID;

@Component
public class GuestService {

    private static List<String> firstNames = Arrays.asList("Joco", "Misi", "Béla", "Füles", "Réka", "Ákos", "Ürsiklosi", "Sándi", "Peti", "Megatron");

    private static List<String> lastNames = Arrays.asList("Gáspár", "Lakatos", "Kovács", "Julius", "Horváth", "Oláh", "Varga", "Balogh", "Orbán");

    private Random random = new Random();

    @Autowired
    private GuestRepository guestRepository;

    public Guest createRandomGuest() {

        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        Guest guest = Guest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(firstName + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com")
                .address(Address.builder().build())
                .build();
        return guest;
    }

    public void addGuest(Guest guest, Address address) {
        guest.setAddress(address);
        address.setGuest(guest);
        guestRepository.save(guest);
    }
}
