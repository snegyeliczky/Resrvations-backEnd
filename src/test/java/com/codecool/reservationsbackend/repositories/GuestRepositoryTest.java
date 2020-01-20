package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.GuestDB;
import com.codecool.reservationsbackend.model.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    GuestDB bela1 =  GuestDB.builder().checkIn(LocalDate.of(2010,2,10))
            .checkOut(LocalDate.of(2010,2,15))
            .name("Béca")
            .email("bela@bela.com")
            .status(Status.CHECKIN)
            .build();

    @Test
    public void saveGuest(){

        guestRepository.save(bela1);
        List<GuestDB> guestDBList = guestRepository.findAll();
        assertThat(guestDBList).hasSize(1);
    }

    @Test
    public void getGuestAtDate(){
        guestRepository.save(bela1);

        List<GuestDB> guestDBList =guestRepository.findByCheckInEquals(LocalDate.of(2010,2,10));

        assertThat(guestDBList).hasSize(1)
                .anyMatch(GuestDB ->GuestDB.getName().equals("Béca"));
    }

}