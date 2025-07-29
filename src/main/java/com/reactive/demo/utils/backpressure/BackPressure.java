package com.reactive.demo.utils.backpressure;

import reactor.core.publisher.Flux;

public class BackPressure {
    public static void main(String[] args){

        Flux.range(1, 100)
                .log()
                .limitRate(10)  // Processes only 10 items at a time
                .subscribe(i -> {
                    try {
                        Thread.sleep(10);  // Simulating slow consumer
                        System.out.println(Thread.currentThread().getName() + " Consumed: " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });


    }
}
