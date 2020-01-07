package com.codecool.reservationsbackend.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    private static AtomicInteger at = new AtomicInteger();

    public int getRoomNumber() {
        return roomNumber;
    }

    private int roomNumber;

    public Room() {
        this.roomNumber = at.getAndIncrement();
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                '}';
    }
}
