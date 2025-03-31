package com.reactive.demo.utils.monoAndFlux;

import reactor.core.publisher.Flux;

public class FluxRunWithLogs {
    public static void main(String[] args) {
        Flux<String> data = Flux.just("Hello - 1","Hello - 2","Hello - 3").log();

        data.subscribe((Object o) -> System.out.println("onNext: " + o));
    }
}
