package com.codecool.reservationsbackend;

import com.codecool.reservationsbackend.model.Guest;
import com.codecool.reservationsbackend.service.GuestCreator;
import com.codecool.reservationsbackend.service.RoomCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ReservationsBackendApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationsBackendApplication.class);

    @Autowired
    private GuestCreator guestCreator;

    @Autowired
    private RoomCreator roomCreator;

    public static void main(String[] args) {
        SpringApplication.run(ReservationsBackendApplication.class, args);

    }

    @PostConstruct
    public void afterInit(){
        Guest guest =guestCreator.creatRandomGuest();
        Guest guest2 =guestCreator.creatRandomGuest();
        LOGGER.info(guest.getCheckIn().toString());
        LOGGER.info(guest.getCheckOut().toString());
        LOGGER.info(guest2.getCheckIn().toString()+" -2");
        LOGGER.info(guest2.getCheckOut().toString()+ " -2");
    }

}
