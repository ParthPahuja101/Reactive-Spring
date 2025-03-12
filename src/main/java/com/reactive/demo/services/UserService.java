package com.reactive.demo.services;

import com.reactive.demo.models.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public Flux<User> getUserList() {
        return Flux.fromStream(userList.stream());
    }

    public void createUserList() {
        userList.add(new User("User1","Email1"));
        userList.add(new User("User2","Email2"));
        userList.add(new User("User3","Email3"));
    }
}
