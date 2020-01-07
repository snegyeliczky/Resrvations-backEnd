package com.codecool.reservationsbackend;

import com.codecool.reservationsbackend.model.Room;
import com.codecool.reservationsbackend.service.RoomCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReservationsBackendApplication {

    @Autowired
    private RoomCreator roomCreator;

    public static void main(String[] args) {
        SpringApplication.run(ReservationsBackendApplication.class, args);
    }

    @PostConstruct
    public void roomTest() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(roomCreator.roomCreator());
    }
}
