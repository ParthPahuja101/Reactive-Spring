package com.reactive.demo;

import com.reactive.demo.reactiveController.UserReactiveController;
import com.reactive.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@WebFluxTest(UserReactiveController.class)
public class ControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserService userService;

    @Test
    public void testGetUserById() {

        Mockito.when(userService.getUserFlux()).thenReturn(Flux.just(1, 2, 3, 4));


        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .isEqualTo(Arrays.asList(1, 2, 3, 4));
    }
}

