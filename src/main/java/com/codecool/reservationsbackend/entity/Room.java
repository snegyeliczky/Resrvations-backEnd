package com.codecool.reservationsbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private static AtomicInteger at = new AtomicInteger(1);

    private int roomNumber;

    private boolean reserved;

    public Room() {
        this.roomNumber = at.getAndIncrement();
    }

    public boolean isReserved() {
        return reserved;
    }

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Hotel hotel;

    @Singular
    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private List<Guest> guests;
}
