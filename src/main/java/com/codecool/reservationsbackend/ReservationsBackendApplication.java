package com.codecool.reservationsbackend;

import com.codecool.reservationsbackend.entity.GuestDB;
import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.model.Status;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.service.GuestCreator;
import com.codecool.reservationsbackend.service.RoomCreator;
import com.codecool.reservationsbackend.service.RoomStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class ReservationsBackendApplication {


    @Autowired
    private GuestRepository guestRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationsBackendApplication.class, args);

    }

    @Bean
    @Profile("production")
    public CommandLineRunner afterInit(){

        return args -> {
            GuestDB bela1 =  GuestDB.builder().checkIn(LocalDate.of(2010,2,10))
                    .checkOut(LocalDate.of(2010,2,15))
                    .name("Béca")
                    .email("bela@bela.com")
                    .status(Status.CHECKIN)
                    .build();

            guestRepository.save(bela1);
        };
    }



}
