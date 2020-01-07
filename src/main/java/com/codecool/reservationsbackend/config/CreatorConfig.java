package com.codecool.reservationsbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class CreatorConfig {

    @Bean
    public List <LocalDate> dateCreator(){
        List<LocalDate> localDates = new ArrayList<>();
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.of(2021, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        long checkOut = ThreadLocalRandom.current().nextLong(randomDay,randomDay+10);

        LocalDate arriveingDate = LocalDate.ofEpochDay(randomDay);
        LocalDate checkoutDate = LocalDate.ofEpochDay(checkOut);

        localDates.add(arriveingDate);
        localDates.add(checkoutDate);

        return localDates;
    }

}
