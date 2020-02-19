package com.codecool.reservationsbackend.service;


import com.codecool.reservationsbackend.entity.*;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.repositories.ReservationRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class ReservationService {

    private static List<String> firstNames = Arrays.asList("Joco", "Misi", "Béla", "Füles", "Réka", "Ákos", "Ürsiklosi", "Sándi", "Peti", "Megatron,");

    private static List<String> lastNames = Arrays.asList("Gáspár", "Lakatos", "Kovács", "Julius", "Horváth", "Oláh", "Varga", "Balogh", "Orbán");

    @Autowired
    private RandomDateCreator randomDateCreator;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    private Random random = new Random();

    public void addNewReservation(Reservation reservation) {
        Guest guestByEmail = guestRepository.findGuestByEmail(reservation.getGuest().getEmail());

        if (guestByEmail == null) {
            guestRepository.save(reservation.getGuest());
            guestByEmail = guestRepository.findGuestByEmail(reservation.getGuest().getEmail());
        }
        reservation.setHotel(hotelRepository.findAll().get(0));
        reservation.setStatus(Status.CHECKIN);
        reservation.setGuest(guestByEmail);
        guestByEmail.addReservation(reservation);
        reservationRepository.save(reservation);
    }

    public Reservation createRandomReservation(Hotel hotel) {
        List<LocalDate> dates = randomDateCreator.dateCreator();
        //reservationRepository.save(reservation);
        return Reservation.builder()
                .checkIn(dates.get(0))
                .checkOut(dates.get(1))
                .hotel(hotel)
                .price(random.nextDouble() + 20000000)
                .paymentMethod(PaymentMethod.values()[random.nextInt(PaymentMethod.values().length)])
                .status(Status.values()[random.nextInt(Status.values().length)])
                .build();

    }

    public List<Room> getAvailableRoomsByDates(LocalDate checkIn, LocalDate checkOut) {

        List<Reservation> reservations = reservationRepository.getUnavailableReservationsByCheckInAndCheckOut(checkIn, checkOut);
        List<Room> availableRooms = roomRepository.findAll();
        if (reservations != null) {
            for (Reservation reservation : reservations) {
                if (reservation.getRoomId() != null) {
                    Room room = roomRepository.getOne(reservation.getRoomId());
                    if (room != null)
                        availableRooms.remove(room);
                }
            }
        }


        return availableRooms;
    }
}
