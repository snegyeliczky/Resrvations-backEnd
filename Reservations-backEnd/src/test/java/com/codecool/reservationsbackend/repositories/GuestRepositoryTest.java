package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Address;
import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.entity.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    /*
    private Guest bela1 =  Guest.builder().checkIn(LocalDate.of(2010,2,10))
            .checkOut(LocalDate.of(2010,2,15))
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

        List<Guest> guestDBList = guestRepository.findByCheckInEquals(LocalDate.of(2010, 2, 10));

        assertThat(guestDBList).hasSize(1)
                .anyMatch(GuestDB -> GuestDB.getName().equals("Béca"));
    }

    @Test
    public void findGuestsByStatus() {
        Guest anna = Guest.builder()
                .name("Anna")
                .status(Status.CHECKIN)
                .build();
        Guest chris = Guest.builder()
                .name("Chris")
                .status(Status.CHECKIN)
                .build();
        Guest john = Guest.builder()
                .name("John")
                .status(Status.CHECKIN)
                .build();

        guestRepository.saveAll(Arrays.asList(anna, chris, john));

        List<Guest> guestsByStatus = guestRepository.findGuestsByStatus(Status.CHECKIN);

        assertThat(guestsByStatus)
                .hasSize(3)
                .allMatch(guest -> guest.getStatus().equals(Status.CHECKIN));
    }

    @Test
    public void findGuestsByStatusAndCheckInIsLike() {
        Guest anna = Guest.builder()
                .name("Anna")
                .status(Status.CHECKIN)
                .checkIn(LocalDate.of(2012, 2, 2))
                .build();
        Guest chris = Guest.builder()
                .name("Chris")
                .status(Status.CHECKOUT)
                .checkIn(LocalDate.of(2013, 3, 3))
                .build();
        Guest john = Guest.builder()
                .name("John")
                .status(Status.CHECKIN)
                .checkIn(LocalDate.of(2014, 4, 4))
                .build();

        guestRepository.saveAll(Arrays.asList(anna, chris, john));

        List<Guest> foundedGuests = guestRepository.findGuestsByStatusAndCheckInIsLike(
                Status.CHECKIN,
                LocalDate.of(2012, 2, 2));

        assertThat(foundedGuests).hasSize(1);
    }


    @Test
    public void changeStatus(){
        guestRepository.save(bela1);

        guestRepository.updateStatus(Status.CHECKOUT,bela1.getId());

        List<Guest> guests = guestRepository.findGuestsByStatus(Status.CHECKOUT);

        assertThat(guests).hasSizeGreaterThanOrEqualTo(1)
                .anyMatch(guest -> guest.getName().equals("Béca") && guest.getStatus()==Status.CHECKOUT);

    }

    @Test
    public void updateGuestRoom() {
        Guest anna = Guest.builder()
                .name("Anna")
                .status(Status.CHECKIN)
                .build();

        Room room = Room.builder().build();

        guestRepository.save(anna);
        roomRepository.save(room);

        guestRepository.updateGuestRoom(room, anna.getId(),room.getRoomNumber());

        List<Guest> guests = guestRepository.findAll();

        assertThat(guests)
                .anyMatch(guest -> guest.getRoom().equals(room));


    }


     */
}