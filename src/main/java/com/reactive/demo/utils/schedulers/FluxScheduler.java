package com.reactive.demo.utils.schedulers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxScheduler {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Flux.range(1, 10)
                .subscribeOn(Schedulers.immediate()) // Immediate scheduler
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " - " + i));

    }


}

class FluxSchedulerSingle {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Flux<Integer>  flux = Flux.range(1, 10);
        flux.subscribeOn(Schedulers.single()) // Single-threaded scheduler
            .subscribe(i -> System.out.println(Thread.currentThread().getName() + " - " + i));

//        Thread.sleep(2000);

//        flux.subscribeOn(Schedulers.single())
//                .doOnNext(i -> System.out.println(Thread.currentThread().getName() + " - " + i))
//                .blockLast(); // Block until the last element is processed


    }


}

class FluxSchedulerParallel {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Flux.range(1, 10)
                .parallel()  // Converts Flux into a parallel stream
                .runOn(Schedulers.parallel())  // Distributes elements across multiple parallel threads
                .doOnNext(i -> System.out.println(Thread.currentThread().getName() + " - " + i))
                .sequential()  // Converts back to sequential Flux (if needed)
                .blockLast();


    }


}


class FluxSchedulerElastic {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Flux.range(1, 10)
                .parallel()  // Converts Flux into a parallel stream
                .runOn(Schedulers.boundedElastic())  // Distributes elements across multiple parallel threads
                .doOnNext(i -> System.out.println(Thread.currentThread().getName() + " - " + i))
                .sequential()  // Converts back to sequential Flux (if needed)
                .blockLast();


    }


}
