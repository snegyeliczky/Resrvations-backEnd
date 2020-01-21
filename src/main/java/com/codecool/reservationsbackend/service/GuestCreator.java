package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class GuestCreator {

    private static List<String> names = Arrays.asList("Joco", "Misi", "Béla", "Füles", "Réka", "Ákos", "Ürsiklosi", "Sándi");
    @Autowired
    private RandomDateCreator randomDateCreator;

    public Guest createRandomGuest() {
        Guest guest = new Guest();
        Random random = new Random();

        List<LocalDate> dates = randomDateCreator.dateCreator();
        guest.setCheckIn(dates.get(0));
        guest.setCheckOut(dates.get(1));
        String name = names.get(random.nextInt(names.size()));
        guest.setName(name);
        guest.getAddress().setEmail(name + "@gmail.com");
        guest.setStatus(Status.values()[random.nextInt(Status.values().length)]);

        return guest;
    }
}
