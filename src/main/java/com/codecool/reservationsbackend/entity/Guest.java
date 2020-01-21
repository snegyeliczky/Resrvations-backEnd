package com.codecool.reservationsbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Guest {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate checkIn;

    private LocalDate checkOut;

    ;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Room room;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Hotel hotel;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;


}
