package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Reservation;
import com.codecool.reservationsbackend.repositories.ReservationRepository;
import com.codecool.reservationsbackend.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sort")
public class FilterController {

    @Autowired
    private SortService sortService;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/all")
    public List<Reservation> getAllSorted(
            @RequestParam(value = "sort", required = true) String sort
    ){
        return sortService.sortAllReservetion(sort);
    }

    @GetMapping()
    public List<Reservation> getSortedReservations(
            @RequestParam( value = "date", required = true) String date,
            @RequestParam(value = "sort", required = true) String sort
    ){
        return sortService.SortReservations(date,sort);
    }

}
