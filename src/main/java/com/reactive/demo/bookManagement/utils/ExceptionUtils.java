package com.reactive.demo.bookManagement.utils;

import reactor.core.publisher.Flux;

import java.io.Serializable;

public class ExceptionUtils {

    // onErrorReturn()
    public static void main(String[] args) {
        Flux<Serializable> data = Flux.concat(
                Flux.just("Hello - 1", "Hello - 2"),
                Flux.error(new RuntimeException("Simulated Exception"))
        );

        data.onErrorReturn("Fallback Value")
                .doOnNext(System.out::println)
                .blockLast();
    }


    // onErrorResume()
//    public static void main(String[] args) {
//        Flux<Serializable> data = Flux.concat(
//                Flux.just("Hello - 1", "Hello - 2"),
//                Flux.error(new RuntimeException("Simulated Exception"))
//        );
//        Flux<Serializable> backupData = Flux.just("Hello - 3", "Hello - 4");
//
//        data.onErrorResume(RuntimeException.class, throwable -> backupData)
//                .doOnNext(System.out::println)
//                .blockLast();
//    }


    //  onErrorMap()
//    public static void main(String[] args) {
//        Flux<Serializable> data = Flux.concat(
//                Flux.just("Hello - 1", "Hello - 2"),
//                Flux.error(new RuntimeException("Simulated Exception"))
//        );
//
//        data.onErrorMap(throwable -> new IllegalStateException("Mapped Exception: " + throwable.getMessage()))
//                .doOnError(error -> System.out.println("Error occurred: " + error.getMessage()))
//                .subscribe(
//                        System.out::println,
//                        throwable -> System.out.println("Final Error: " + throwable.getMessage())
//                );
//    }


    // doOnError()
//    public static void main(String[] args) {
//        Flux<Serializable> data = Flux.concat(
//                Flux.just("Hello - 1", "Hello - 2"),
//                Flux.error(new RuntimeException("Simulated Exception"))
//        );
//
//        data.doOnError(error -> System.out.println("Error logged: " + error.getMessage()))
//                .subscribe(System.out::println);
//    }
}
