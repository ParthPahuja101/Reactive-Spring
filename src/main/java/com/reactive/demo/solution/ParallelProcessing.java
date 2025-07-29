package com.reactive.demo.solution;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelProcessing {
    public static void main(String[] args) {
        Flux.range(1, 100)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(n -> n * n)
                .doOnNext(n -> System.out.println(Thread.currentThread().getName() + " - " + n))
                .sequential()
                .blockLast();
    }
}

class ErrorHandling {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1, 10);

        flux.subscribeOn(Schedulers.single())
                .subscribe(i -> System.out.println("Single: " + Thread.currentThread().getName() + " - " + i));

        flux.parallel()  // Converts Flux into a parallel stream
                .runOn(Schedulers.parallel())  // Distributes elements across multiple parallel threads
                .doOnNext(i -> System.out.println("Parallel: " + Thread.currentThread().getName() + " - " + i))
                .sequential()  // Converts back to sequential Flux (if needed)
                .blockLast();
    }
}