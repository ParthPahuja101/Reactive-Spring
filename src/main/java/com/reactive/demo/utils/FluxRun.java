package com.reactive.demo.utils;

import reactor.core.publisher.Flux;

public class FluxRun {
    public static void main(String[] args) {
        Flux<String> data = Flux.just("Hello - 1","Hello - 2","Hello - 3");

        data.subscribe(System.out::println);
    }
}

