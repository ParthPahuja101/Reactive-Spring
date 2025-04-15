package com.reactive.demo;

import com.reactive.demo.models.User;
import com.reactive.demo.reactiveController.UserReactiveController;
import com.reactive.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import java.util.List;

@WebFluxTest(UserReactiveController.class)
public class ControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserService userService;

    @Test
    public void testGetUserWithSize() {

        User mockUser = new User(1, "John", "john@example.com");
        Mockito.when(userService.getUserFlux()).thenReturn(Flux.just(mockUser));


        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class).hasSize(1);
    }

    @Test
    public void testGetUserWithContent() {

        User mockUser = new User(1, "John", "john@example.com");
        Mockito.when(userService.getUserFlux()).thenReturn(Flux.just(mockUser));


        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class)
                .consumeWith(response -> {
                    List<User> users = response.getResponseBody();
                    assert users != null;
                    assert users.size() == 1;
                    User responseUser = users.get(0);
                    assert responseUser.id == mockUser.id;
                    assert responseUser.name.equals(mockUser.name);
                    assert responseUser.email.equals(mockUser.email);
                });
    }

    @Test
    public void testGetUserWithException() {
        // Mock the service to return an error
        Mockito.when(userService.getUserFlux())
                .thenReturn(Flux.error(new RuntimeException("User not found")));

        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .isEqualTo("Something went wrong!");
    }
}

