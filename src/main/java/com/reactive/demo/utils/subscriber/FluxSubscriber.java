package com.reactive.demo.utils.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FluxSubscriber implements Subscriber {
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe");
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println("onNext: " + o);
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
