package com.codecool.reservationsbackend.userservice.controller;

import com.codecool.reservationsbackend.userservice.entity.AppUser;
import com.codecool.reservationsbackend.userservice.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/newuser")
    public ResponseEntity addNewUser(@RequestBody AppUser appUser) {
        if (adminService.isUsernameIsUnique(appUser.getUsername())) {
            adminService.addNewUser(appUser);
            return ResponseEntity.ok("");
        }
        return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }

}
