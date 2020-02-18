package com.codecool.reservationsbackend.userservice.respository;

import com.codecool.reservationsbackend.userservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUsername(String username);
}
