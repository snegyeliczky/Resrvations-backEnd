package com.codecool.reservationsbackend.userservice.controller;


import com.codecool.reservationsbackend.userservice.entity.AppUser;
import com.codecool.reservationsbackend.userservice.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public AppUser getAppUserByUsername(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }


}
