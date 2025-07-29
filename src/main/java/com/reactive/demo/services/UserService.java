package com.reactive.demo.services;

import com.reactive.demo.models.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import java.util.List;

@Service
public class UserService {


    User user1 = new User(1,"Harry", "harry@griffindor.com");
    User user2 = new User(2,"Ron", "ron@griffindor.com");
    User user3 = new User(3,"Hermoine", "hermoine@griffindor.com");

    public Flux<User> getUserFlux() {
        Flux<User> flux= Flux.just(user1,user2,user3);
        return flux;
    }

    public List<User> getUserList() {
        List<User> list=List.of(user1,user2,user3);

        return list;
    }

    public List<User> getUserById(int id) {
        List<User> list=List.of(user1,user2,user3);

        return list.stream().filter(user -> user.id == id).toList();
    }

}
