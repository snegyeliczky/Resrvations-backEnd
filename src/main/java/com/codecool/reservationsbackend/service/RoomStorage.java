package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomStorage {
    private List<Room> roomList = new ArrayList<>();

    public RoomStorage(RoomCreator roomCreator) {
        this.roomCreator = roomCreator;
    }

    @Autowired
    private RoomCreator roomCreator;

    public Room addRoom() {
        Room room = roomCreator.createRoom();
        this.roomList.add(room);
        return room;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
