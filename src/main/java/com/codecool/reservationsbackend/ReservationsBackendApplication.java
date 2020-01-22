package com.codecool.reservationsbackend;

import com.codecool.reservationsbackend.entity.*;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.service.GuestCreator;
import com.codecool.reservationsbackend.service.RoomCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ReservationsBackendApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationsBackendApplication.class);

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private GuestCreator guestCreator;

    @Autowired
    private RoomCreator roomCreator;

    public static void main(String[] args) {
        SpringApplication.run(ReservationsBackendApplication.class, args);

    }

    @Bean
    @Profile("production")
    public CommandLineRunner afterInit() {

        return args -> {

            if (hotelRepository.findAll().size() == 0) {
                Random random = new Random();
                List<Room> rooms = new ArrayList<>();
                List<Guest> guests = new ArrayList<>();

                Hotel hotel = Hotel.builder()
                        .name("Budapest best Hotel!")
                        .build();


                for (int i = 0; i < 8; i++) {
                    Room room = roomCreator.createRoom(hotel);
                    rooms.add(room);
                }



                for (int i = 0; i < 10; i++) {

                    Guest guest = guestCreator.createRandomGuest(hotel);

                    if (guest.getStatus().equals(Status.IN)) {
                        guest.setRoom(rooms.get(random.nextInt(rooms.size())));
                    }


                    guests.add(guest);
                }

                hotel.setRooms(rooms);
                hotel.setGuests(guests);
                hotelRepository.save(hotel);
            }



        };
    }


}
