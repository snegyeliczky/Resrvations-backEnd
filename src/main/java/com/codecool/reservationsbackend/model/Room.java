package com.codecool.reservationsbackend.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    private static AtomicInteger at = new AtomicInteger();
    private int roomNumber;

    public Room() {
        this.roomNumber = at.getAndIncrement();
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
