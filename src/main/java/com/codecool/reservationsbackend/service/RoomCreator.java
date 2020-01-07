package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomCreator {

    public Room roomCreator() {
        Room room = new Room();
        return room;
    }
}
