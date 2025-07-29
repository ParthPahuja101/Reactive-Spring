package com.reactive.demo.utils.backpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BufferBackpressure {
    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> fastPublisher = Flux.range(1, 50)
                .delayElements(Duration.ofMillis(10)); // Emits data fast

        fastPublisher
                .onBackpressureBuffer().doOnNext(System.out::println) // Buffers all data if the subscriber is slow
                .blockLast();

//        Thread.sleep(5000); // Keep application running
    }
}
