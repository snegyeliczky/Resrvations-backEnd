package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Guest;
import com.codecool.reservationsbackend.entity.Hotel;
import com.codecool.reservationsbackend.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomService {

    public Room createRoom(Hotel hotel) {
        return new Room(hotel);
    }


}
