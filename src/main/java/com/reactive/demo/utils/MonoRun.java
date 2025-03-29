package com.reactive.demo.utils;

import reactor.core.publisher.Mono;

public class MonoRun {
    public static void main(String[] args) {
        Mono<String> data = Mono.just("Hello!!");

        data.subscribe(System.out::println);
    }


}
