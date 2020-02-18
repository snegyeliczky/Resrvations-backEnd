package com.codecool.reservationsbackend.init;

import com.codecool.reservationsbackend.entity.*;
import com.codecool.reservationsbackend.repositories.*;
import com.codecool.reservationsbackend.service.GuestService;
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
    private GuestService guestService;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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
                List<Guest> guests = new ArrayList<>();
                List<Reservation> reservations = new ArrayList<>();

                // CREATING HOTEL
                Hotel hotel = Hotel.builder()
                        .name("Budapest best Hotel!")
                        .build();


                // CREATING ROOMS
                for (int i = 0; i < 8; i++) {
                    Room room = roomService.createRoom(hotel);
                    rooms.add(room);
                }

                hotel.setRooms(rooms);
                roomRepository.saveAll(rooms);


                // CREATING GUESTS
                for (int i = 0; i < 10; i++) {
                    Guest guest = guestService.createRandomGuest();
                    guests.add(guest);
                }

                // CREATING RESERVATIONS AND IT'S GUESTS
                for (int i = 0; i < 10; i++) {
                    Reservation reservation = reservationService.createRandomReservation(hotel);
                    if (reservation.getStatus().equals(Status.IN)) {
                        List<Room> availableRooms = reservationService.getAvailableRoomsByDates(
                                reservation.getCheckIn(),
                                reservation.getCheckOut());
                        Room randomRoom = availableRooms.get(random.nextInt(availableRooms.size()));
                        reservation.setRoomId(randomRoom.getId());
                    }
                    reservations.add(reservation);
                }

                for (int i = 0; i < reservations.size(); i++) {
                    reservations.get(i).setGuest(guests.get(i));
                    guests.get(i).setReservations(Arrays.asList(reservations.get(i)));
                }

                hotel.setReservations(reservations);
                reservationRepository.saveAll(reservations);
                log.info(hotel.getReservations().get(0).toString());
                hotelRepository.save(hotel);


                // CREATING ADMIN
                AppUser adminOfAdmins = AppUser.builder()
                        .username("cezar")
                        .password(passwordEncoder.encode("salata"))
                        .roles(Arrays.asList(Roles.values()))
                        .build();
                userRepository.save(adminOfAdmins);
            }
        };
    }
}
