package com.reactive.demo.services;

import com.reactive.demo.models.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    public Flux<Integer> getUserFlux() {
        Flux<Integer> flux= Flux.just(1,2,3,4);
        return flux;
    }

    public List<Integer> getUserList() {
        List<Integer> list= new ArrayList<>(List.of(1,2,3,4));

        return list;
    }

}
