package com.codecool.reservationsbackend.entity;

import com.codecool.reservationsbackend.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RoomDBTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void saveOneRoom() {
        RoomDB roomDB = RoomDB.builder()
                .reserved(true)
                .build();

        roomRepository.save(roomDB);

        assertThat(roomRepository.findAll()).hasSize(1);
    }

}