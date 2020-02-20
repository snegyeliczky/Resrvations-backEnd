package com.codecool.reservationsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Guest {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    @Singular
    @ToString.Exclude
    @OneToMany(mappedBy = "guest", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations = new LinkedList<>();

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
