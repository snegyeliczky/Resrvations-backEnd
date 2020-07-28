package com.codecool.reservationsbackend.service;


import com.codecool.reservationsbackend.entity.*;
import com.codecool.reservationsbackend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationService {

    private final AddressRepository addressRepository;

    private final RandomDateCreator randomDateCreator;

    private final GuestRepository guestRepository;

    private final ReservationRepository reservationRepository;

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    private final Random random = new Random();

    public void addNewReservation(Reservation reservation) {
        Guest guestByEmail = guestRepository.findGuestByEmail(reservation.getGuest().getEmail());

        if (guestByEmail == null) {
            reservation.getGuest().getAddress().setGuest(reservation.getGuest());
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

    public List<Room> getAvailableRoomIdsForToday(LocalDate date) {

        List<Reservation> checkIns = reservationRepository.findByCheckInEquals(date);
        List<Long> reservedRoomIdes = checkIns.stream()
                .map(Reservation::getRoomId)
                .collect(Collectors.toList());

        List<Room> allRooms = roomRepository.findAll();
        ArrayList<Room> freeRooms = new ArrayList<>();
            for (Room room:allRooms) {
                if (!reservedRoomIdes.contains(room.getId())){
                    freeRooms.add(room);
                }
        }
        return freeRooms;
    }


    public void updateReservation(Reservation reservation) {
        addressRepository.save(reservation.getGuest().getAddress());
        guestRepository.save(reservation.getGuest());
        reservationRepository.save(reservation);
    }
}
