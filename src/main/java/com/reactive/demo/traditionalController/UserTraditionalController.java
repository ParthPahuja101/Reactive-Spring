package com.reactive.demo.traditionalController;

import com.reactive.demo.models.User;
import com.reactive.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users-traditional")
public class UserTraditionalController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    public List<User> getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

}
