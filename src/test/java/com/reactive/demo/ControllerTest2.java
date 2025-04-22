package com.reactive.demo;

import com.reactive.demo.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
public class ControllerTest2 {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetUserWithSize() {


        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class).hasSize(3);
    }

    @Test
    public void testGetUserWithContent() {



        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class)
                .consumeWith(response -> {
                    List<User> users = response.getResponseBody();
                    assert users != null;
                    User responseUser = users.get(0);
                    assert responseUser.id == 1;
                    assert responseUser.name.equals("Harry");
                    assert responseUser.email.equals("harry@griffindor.com");
                });
    }
}
