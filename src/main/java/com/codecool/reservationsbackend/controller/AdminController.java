package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.AppUser;
import com.codecool.reservationsbackend.entity.Roles;
import com.codecool.reservationsbackend.service.AdminService;
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
    public ResponseEntity addNewUser(@RequestBody AppUser appUser, String role) {
        System.out.println(appUser);
        System.out.println(role);
        //adminService.addNewUser(appUser, Roles.valueOf(role));
        return ResponseEntity.ok("");
    }

}
