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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Bean
    public CommandLineRunner afterInit() {

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
                    addRoomToReservation(random, reservations, reservation);
                }


                for (int i = 0; i < reservations.size(); i++) {
                    reservations.get(i).setGuest(guests.get(i));
                    guests.get(i).setReservations(Arrays.asList(reservations.get(i)));
                }

                for (int i = 0; i < 4; i++) {
                    Reservation reservation = Reservation.builder()
                            .checkIn(LocalDate.now())
                            .checkOut(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                                    .nextLong(LocalDate.now().toEpochDay() + 1,
                                    LocalDate.now().toEpochDay() + 15)) )
                            .hotel(hotel)
                            .status(Status.values()[i % 2 == 0 ? 0 : 1])
                            .guest(guestService.createRandomGuest())
                            .price(random.nextDouble() + 20000000)
                            .paymentMethod(PaymentMethod.values()[random.nextInt(PaymentMethod.values().length)])
                            .build();
                    addRoomToReservation(random, reservations, reservation);
                }





                hotel.setReservations(reservations);
                reservationRepository.saveAll(reservations);
                hotelRepository.save(hotel);
            }
        };
    }

    private void addRoomToReservation(Random random, List<Reservation> reservations, Reservation reservation) {
        if (reservation.getStatus().equals(Status.IN)) {
            List<Room> availableRooms = reservationService.getAvailableRoomsByDates(
                    reservation.getCheckIn(),
                    reservation.getCheckOut());
            Room randomRoom = availableRooms.get(random.nextInt(availableRooms.size()));
            reservation.setRoomId(randomRoom.getId());
        }

        reservations.add(reservation);
    }
}
