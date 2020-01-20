package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
