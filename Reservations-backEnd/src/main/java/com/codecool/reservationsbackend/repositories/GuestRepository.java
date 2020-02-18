package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findGuestByEmail(String email);

//    @Query("UPDATE Guest g SET g.name = :name WHERE g.id = :guestId")
//    @Modifying(clearAutomatically = true)
//    void editGuestById(@Param("name") String name, @Param("guestId") Long guestId);

}
