package com.codecool.reservationsbackend.security;

import com.codecool.reservationsbackend.entity.AppUser;
import com.codecool.reservationsbackend.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository users;

    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = users.findByUsername(username);
        if (appUser == null) {
            throw (new UsernameNotFoundException("Username: " + username + " not found"));
        }
        User user = new User(appUser.getUsername(), appUser.getPassword(),
                appUser.getRolesInString().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
        System.out.println(user.toString());
        return user;
    }
}
