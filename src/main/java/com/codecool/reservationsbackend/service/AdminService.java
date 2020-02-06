package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.AppUser;
import com.codecool.reservationsbackend.entity.Roles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AdminService {

    private PasswordEncoder passwordEncoder;

    public void addNewUser(AppUser user, Roles role) {
        AppUser newUser = new AppUser();
        /**
        newUser.setUsername(use);


        AppUser adminOfAdmins = AppUser.builder()
                .password(passwordEncoder.encode("salata"))
                .username("cezar")
                .roles(Arrays.asList(Roles.ADMIN,Roles.SLAVE))
                .build();
         **/
    }
}
