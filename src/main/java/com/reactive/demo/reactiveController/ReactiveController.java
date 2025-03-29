package com.reactive.demo.reactiveController;

import com.reactive.demo.models.Joke;
import com.reactive.demo.services.JokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jokes")
public class ReactiveController {

    @Autowired
    JokesService jokesService;

    @GetMapping
    public Mono<Joke> getRandomJoke(){
        return jokesService.getRandomJoke();
    }
}
