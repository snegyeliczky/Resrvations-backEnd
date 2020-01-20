package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomCreator {

    public Room createRoom() {
        return new Room();
    }
}
