package com.codecool.reservationsbackend.controller;

import com.codecool.reservationsbackend.entity.Room;
import com.codecool.reservationsbackend.service.RoomStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomStorage roomStorage;

    @GetMapping("/list")
    public List<Room> roomList() {
        return roomStorage.getRoomList();
    }

    @GetMapping("/add")
    public Room addRoom() {
        return roomStorage.addRoom();
    }
}
