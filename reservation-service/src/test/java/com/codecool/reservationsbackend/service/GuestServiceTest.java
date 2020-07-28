package com.codecool.reservationsbackend.service;

import com.codecool.reservationsbackend.entity.Guest;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GuestService.class)
@ActiveProfiles("test")
public class GuestServiceTest {

    @Autowired
    private GuestService guestService;

    @Test
    public void createRandomGuest() {
        assertThat(guestService.createRandomGuest()).isNotNull();
    }

}
