package com.reactive.demo.utils.monoAndFlux;

import com.reactive.demo.utils.subscriber.FluxSubscriber;
import reactor.core.publisher.Flux;

public class FluxRunWithSubscriber {
    public static void main(String[] args) {
        Flux<String> data = Flux.just("Hello - 1","Hello - 2","Hello - 3");

        data.subscribe(new FluxSubscriber());
    }
}
