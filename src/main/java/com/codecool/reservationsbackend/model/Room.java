package com.codecool.reservationsbackend.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    private static AtomicInteger at = new AtomicInteger(1);
    private int roomNumber;
    private boolean reserved;

    public Room() {
        this.roomNumber = at.getAndIncrement();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", reserved=" + reserved +
                '}';
    }
}
