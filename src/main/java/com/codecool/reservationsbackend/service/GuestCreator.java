package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class GuestCreator {

    private static List<String> names= Arrays.asList("Joco","Misi","Béla","Füles","Réka","Ákos","Ürsiklosi","Sándi");

    @Autowired
    private List<LocalDate> dateCreator;

    public Guest creatRandomGuest(){
        Guest guest = new Guest();
        Random random = new Random();

        List<LocalDate> dates = dateCreator;
        guest.setCheckIn(dates.get(0));
        guest.setCheckOut(dates.get(1));


        return guest;
    }
}
