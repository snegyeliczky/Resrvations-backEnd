package com.codecool.reservationsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate checkIn;

    private LocalDate checkOut;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double price;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private boolean isCityTaxIncluded;

    private Long roomId;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Guest guest;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    private Hotel hotel;


}
