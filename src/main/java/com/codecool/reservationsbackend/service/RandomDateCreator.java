package com.codecool.reservationsbackend.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomDateCreator {

    public List<LocalDate> dateCreator() {
        List<LocalDate> localDates = new ArrayList<>();
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        long checkOut = ThreadLocalRandom.current().nextLong(randomDay + 1, randomDay + 15);

        LocalDate arriveingDate = LocalDate.ofEpochDay(randomDay);
        LocalDate checkoutDate = LocalDate.ofEpochDay(checkOut);

        localDates.add(arriveingDate);
        localDates.add(checkoutDate);

        return localDates;
    }
}
