package com.test.task.back.controller;

import com.test.task.back.dto.DataLoadForm;
import com.test.task.back.type.LoadStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataLoadControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetSuccess(){
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/api/dataload/",
                DataLoadForm.of("http://frontend.tanuki.ru/feeds/raiden-delivery-club/"),
                LoadStatus.class)).isEqualTo(LoadStatus.SUCCESS);
    }
}
