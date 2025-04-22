package com.reactive.demo.traditionalController;

import com.reactive.demo.models.User;
import com.reactive.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        List<User> users = userService.getUserById(id);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return users;
    }

}
