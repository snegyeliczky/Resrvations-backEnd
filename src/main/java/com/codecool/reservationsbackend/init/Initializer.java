package com.codecool.reservationsbackend.init;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.entity.Status;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.service.GuestCreator;
import com.codecool.reservationsbackend.service.RoomCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@Slf4j
@Profile("production")
public class Initializer {


    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private GuestCreator guestCreator;

    @Autowired
    private RoomCreator roomCreator;

    @Bean
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
                        Room rom;
                        do {
                            rom = rooms.get(random.nextInt(rooms.size()));
                        } while (rom.getGuest() != null);
                        rom.setGuest(guest);
                        guest.setRoom(rom);
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
