package com.codecool.reservationsbackend.init;

import com.codecool.reservationsbackend.entity.*;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.repositories.UserRepository;
import com.codecool.reservationsbackend.service.ReservationService;
import com.codecool.reservationsbackend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
@Slf4j
@Profile("production")
public class Initializer {


    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReservationService reservationService;

    @Bean
    public CommandLineRunner afterInit() {

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return args -> {

            if (hotelRepository.findAll().size() == 0) {
                Random random = new Random();
                List<Room> rooms = new ArrayList<>();
                List<Reservation> reservations = new ArrayList<>();

                Hotel hotel = Hotel.builder()
                        .name("Budapest best Hotel!")
                        .build();


                for (int i = 0; i < 8; i++) {
                    Room room = roomService.createRoom(hotel);
                    rooms.add(room);
                }



                for (int i = 0; i < 10; i++) {

                    Reservation reservation = reservationService.createRandomReservation(hotel);

                    if (reservation.getStatus().equals(Status.IN) || reservation.getStatus().equals(Status.CHECKOUT)) {

                        Room randomRoom = reservationService.getAvailableRoomsByDates(reservation.getCheckIn(), reservation.getCheckOut()).get(random.nextInt(rooms.size()));

                        reservation.setRoomId(randomRoom.getId());
                    }


                    reservations.add(reservation);
                }

                AppUser adminOfAdmins = AppUser.builder()
                        .username("cezar")
                        .password(passwordEncoder.encode("salata"))
                        .roles(Arrays.asList(Roles.values()))
                        .build();

                userRepository.save(adminOfAdmins);

                hotel.setRooms(rooms);
                hotelRepository.save(hotel);
            }



        };
    }
}
