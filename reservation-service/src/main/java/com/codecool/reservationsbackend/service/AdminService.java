package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.AppUser;
import com.codecool.reservationsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void addNewUser(AppUser user) {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isUsernameIsUnique(String username) {
        List<AppUser> appUserList = userRepository.findAll();
        for (AppUser appUser : appUserList) {
            if (appUser.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}
