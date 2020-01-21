package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Address;
import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    private Guest bela1 =  Guest.builder().checkIn(LocalDate.of(2010,2,10))
            .checkOut(LocalDate.of(2010,2,15))
            .id(1L)
            .name("Béca")
            .address(Address.builder().email("bela@bela.com").build())
            .status(Status.CHECKIN)
            .build();

    @Test
    public void saveGuest(){

        guestRepository.save(bela1);
        List<Guest> guestDBList = guestRepository.findAll();
        assertThat(guestDBList).hasSize(1);
    }

    @Test
    public void getGuestAtDate(){
        guestRepository.save(bela1);

        List<Guest> guestDBList =guestRepository.findByCheckInEquals(LocalDate.of(2010,2,10));

        assertThat(guestDBList).hasSize(1)
                .anyMatch(GuestDB ->GuestDB.getName().equals("Béca"));
    }

    @Test
    public void changeStatus(){
        guestRepository.save(bela1);

        guestRepository.updateStatus(Status.CHECKOUT,1L);

        List<Guest> guests = guestRepository.findGuestsByStatus(Status.CHECKOUT);
        assertThat(guests).hasSizeGreaterThanOrEqualTo(1)
                .anyMatch(guest -> guest.getName()=="Béca" && guest.getStatus()==Status.CHECKOUT);

    }

}