package com.codecool.reservationsbackend.repositories;

import com.codecool.reservationsbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("UPDATE Room r SET r.guest = null WHERE r.id = :roomId")
    @Modifying(clearAutomatically = true)
    int removeGuestFromRoomByRoomId(@Param("roomId") long roomId);

}
