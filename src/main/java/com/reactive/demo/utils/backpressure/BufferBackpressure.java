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
                .onBackpressureBuffer() // Buffers all data if the subscriber is slow
                .subscribe(new Subscriber<Integer>() {
                    private Subscription subscription;
                    private int received = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        System.out.println("Subscribed!");
                        subscription.request(5); // Request only 5 items at a time
                    }

                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Processing: " + item);
                        received++;
                        if (received % 5 == 0) {
                            System.out.println("Requesting next batch of 5...");
                            subscription.request(5);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.err.println("Error: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Processing complete!");
                    }
                });

        Thread.sleep(5000); // Keep application running
    }
}
