package com.codecool.reservationsbackend.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    private static AtomicInteger at = new AtomicInteger(1);
    private int roomNumber;
    private boolean reserved;
    private Guest guest = null;

    public Room() {
        this.roomNumber = at.getAndIncrement();
        this.guest = new Guest();
        guest.setName("Rettenet");
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved() {
        this.reserved = !this.reserved;
        if (reserved = false) {
            this.guest = null;
        }
    }

    public void setReservedAndGuest(boolean reserved, Guest guest) {
        this.reserved = reserved;
        this.guest = reserved ? guest : null;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", reserved=" + reserved +
                '}';
    }
}
