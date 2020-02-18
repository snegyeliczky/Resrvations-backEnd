package com.codecool.reservationsbackend.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Singular
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private List<Room> rooms;

    @Singular
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations;
}
