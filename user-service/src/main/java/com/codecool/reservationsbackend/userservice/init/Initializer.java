package com.codecool.reservationsbackend.userservice.init;

import com.codecool.reservationsbackend.userservice.entity.AppUser;
import com.codecool.reservationsbackend.userservice.entity.Roles;
import com.codecool.reservationsbackend.userservice.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Slf4j
@Profile("production")
public class Initializer {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner afterInit() {

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return args -> {

            // CREATING ADMIN
            AppUser adminOfAdmins = AppUser.builder()
                    .username("cezar")
                    .password(passwordEncoder.encode("salata"))
                    .roles(Arrays.asList(Roles.values()))
                    .build();
            userRepository.save(adminOfAdmins);
        };
    }
}
