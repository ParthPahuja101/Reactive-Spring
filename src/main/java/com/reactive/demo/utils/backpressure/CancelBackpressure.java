package com.reactive.demo.utils.backpressure;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class CancelBackpressure {
    public static void main(String[] args) {
        Flux.range(1, 100)
                .doOnRequest(n -> System.out.println("Requested: " + n))
                .subscribe(new Subscriber<Integer>() {
                    private Subscription subscription;
                    private int received = 0;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        System.out.println("Subscribed!");
                        subscription.request(10); // Request 10 items initially
                    }

                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Processing: " + item);
                        received++;

                        if (received == 10) { // Stop after 10 items
                            System.out.println("Cancelling subscription...");
                            subscription.cancel(); // Cancels the subscription
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
    }
}
