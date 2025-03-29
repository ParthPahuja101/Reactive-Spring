package com.reactive.demo.utils;

import reactor.core.publisher.Flux;

public class FluxRun1 {
    public static void main(String[] args) {

        Flux<Integer> data = Flux.range(1,100);

        data.subscribe(System.out::println);

    }
}
