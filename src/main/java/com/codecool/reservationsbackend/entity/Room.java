package com.codecool.reservationsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Transient
    private static AtomicInteger at = new AtomicInteger(1);

    private int number;

    private int capacity;

    @Enumerated(EnumType.STRING)
    private Type type;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Hotel hotel;


    //Constructors
    public Room(Hotel hotel) {
        this.number = at.getAndIncrement();
        this.hotel = hotel;
    }

    public Room() {
        this.number = at.getAndIncrement();
    }
}
