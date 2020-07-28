package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.*;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({ReservationService.class, RandomDateCreator.class})
@ActiveProfiles("test")
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;


    @Test
    public void createRandomReservation() {

        Hotel hotel = Hotel.builder()
                .name("Budapest best Hotel!")
                .build();

        assertThat(reservationService.createRandomReservation(hotel)).isNotNull();
    }

}
