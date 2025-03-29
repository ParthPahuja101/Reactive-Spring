package com.reactive.demo.traditionalController;

import com.reactive.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users-traditional")
public class UserTraditionalController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<Integer> getAllUsers(){
        return userService.getUserList();
    }
}
