
package com.codecool.reservationsbackend.entity;

import com.codecool.reservationsbackend.repositories.AddressRepository;
import com.codecool.reservationsbackend.repositories.GuestRepository;
import com.codecool.reservationsbackend.repositories.HotelRepository;
import com.codecool.reservationsbackend.repositories.RoomRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")

public class HotelDBTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void saveHotelsToDB() {
        Hotel hotel1 = Hotel.builder()
                .name("First Hotel!")
                .build();

        Hotel hotel2 = Hotel.builder()
                .name("Second Hotel!")
                .build();

        hotelRepository.save(hotel1);
        hotelRepository.save(hotel2);

        assertThat(hotelRepository.findAll())
                .hasSize(2);
    }

    @Test
    public void hotelNameCannotBeNull() {
        Hotel hotel1 = Hotel.builder()
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> hotelRepository.saveAndFlush(hotel1));
    }

    @Test
    public void saveUniqueHotelNameTwice() {
        Hotel hotel1 = Hotel.builder()
                .name("First Hotel!")
                .build();

        hotelRepository.save(hotel1);
        Hotel hotel2 = Hotel.builder()
                .name("First Hotel!")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> hotelRepository.saveAndFlush(hotel2));
    }



}
