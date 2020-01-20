package com.codecool.reservationsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@Builder
@Entity
public class RoomDB {

    @Id
    @GeneratedValue
    private Long id;

    private static AtomicInteger at = new AtomicInteger(1);

    private int roomNumber;

    private boolean reserved;

    public RoomDB() {
        this.roomNumber = at.getAndIncrement();
    }
}
