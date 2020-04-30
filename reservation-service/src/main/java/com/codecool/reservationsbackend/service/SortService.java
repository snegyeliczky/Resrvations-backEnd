package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Reservation;
import com.codecool.reservationsbackend.repositories.ReservationRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SortService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> SortReservations(String date, String sort){
        List<Reservation> reservationsByDate = getReservationsByDate(date);
        switch (sort){
            case "firstName":
                System.out.println("firstName");
                return sortByName(reservationsByDate);
            default:
                return reservationsByDate;
        }

    }

    public List<Reservation> sortAllReservetion(String sort) {
        List<Reservation> allReservation = getAllReservation();
        switch (sort){
            case "firstName":
                System.out.println("firstName");
                return sortByName(allReservation);
            default:
                return allReservation;
        }
    }

    private List<Reservation> sortByName(List<Reservation> reservationList){
        Comparator<Reservation> sortByName = Comparator.comparing((Reservation r) -> r.getGuest().getFirstName());
        Collections.sort(reservationList,sortByName);
        return reservationList;
    }


    private List<Reservation> getReservationsByDate(String date) {
        return reservationRepository.findByCheckInEquals(LocalDate.parse(date));
    }


    private List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }




}
