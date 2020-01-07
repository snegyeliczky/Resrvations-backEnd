package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomStorage {
    private List<Room> roomList = new ArrayList<>();

    public RoomStorage(RoomCreator roomCreator) {
        this.roomCreator = roomCreator;
    }

    private RoomCreator roomCreator;

    public void addRoom() {
        this.roomList.add(roomCreator.createRoom());
    }
}
