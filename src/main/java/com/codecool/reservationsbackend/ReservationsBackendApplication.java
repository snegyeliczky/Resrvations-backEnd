package com.codecool.reservationsbackend;

import com.codecool.reservationsbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ReservationsBackendApplication {

    @Autowired
    private RoomRepository roomRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReservationsBackendApplication.class, args);

    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

        };
    }
    /*@Bean
    @Profile("production")
    public void afterInit(){
        Guest guest =guestCreator.createRandomGuest();
        Guest guest2 =guestCreator.createRandomGuest();
        LOGGER.info(guest.getCheckIn().toString());
        LOGGER.info(guest.getCheckOut().toString());
        LOGGER.info(guest2.getCheckIn().toString()+" -2");
        LOGGER.info(guest2.getCheckOut().toString()+ " -2");
        for (int i = 0; i < 10; i++) {
            roomStorage.addRoom();
        }
    }

     */
}
