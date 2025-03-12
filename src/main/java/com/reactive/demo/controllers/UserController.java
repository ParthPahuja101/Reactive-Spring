package com.reactive.demo.controllers;

import com.reactive.demo.models.User;
import com.reactive.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public Flux<User> getAllUsers(){
        userService.createUserList();
        return userService.getUserList();
    }
}
