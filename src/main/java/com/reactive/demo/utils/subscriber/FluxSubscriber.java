package com.reactive.demo.utils.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FluxSubscriber implements Subscriber {

    Subscription s;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe");
        s=subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Object o) {
        System.out.println("onNext: " + o);
        try {
            Thread.sleep(500);
            s.request(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
