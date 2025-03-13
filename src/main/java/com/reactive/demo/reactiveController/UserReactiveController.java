package com.reactive.demo.reactiveController;

import com.reactive.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/users")
public class UserReactiveController {

    @Autowired
    UserService userService;

    @GetMapping
    public Flux<Integer> getAllUsers(){
        return userService.getUserFlux();
    }
}
