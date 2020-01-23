package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("UPDATE Address a SET a.country = :country, a.city = :city, a.street = :street, a.zipCode = :zipCode, a.email = :email WHERE a.id = :addressID")
    @Modifying(clearAutomatically = true)
    void editAddress(@Param("country") String country,
                     @Param("city") String city,
                     @Param("street") String street,
                     @Param("zipCode") Integer zipCode,
                     @Param("email") String email,
                     @Param("addressId") long addressId);
}
