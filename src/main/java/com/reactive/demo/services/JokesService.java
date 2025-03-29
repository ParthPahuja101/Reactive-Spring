package com.reactive.demo.services;

import com.reactive.demo.exceptions.NotFoundException;
import com.reactive.demo.models.Joke;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JokesService {
    WebClient webClient = WebClient.create("https://official-joke-api.appspot.com");

    public Mono<Joke> getRandomJoke(){
        return webClient.get().uri("/random_joke").retrieve().bodyToMono(Joke.class);
    }

    public Mono<Joke> getJokeById(int id){
        return webClient
                .get()
                .uri("/random_joke/{id}",id)
                .exchangeToMono( clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(Joke.class);  // Extract response body
                    } else if (clientResponse.statusCode().value() == 404){
                        return Mono.error(new NotFoundException("OOPS! Joke not found"));
                    }else {
                        return Mono.error(new RuntimeException("Error: " + clientResponse.statusCode()));
                    }
                });
    }
}
