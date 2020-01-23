package com.codecool.reservationsbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String country;

    private String city;

    private String street;

    private Integer zipCode;

    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    private Guest guest;
}
