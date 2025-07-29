package com.reactive.demo.utils.monoAndFlux;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Helpers {
    public static void main(String[] args) {
        test6();
    }

    public static void empty(){
        Flux<String> flux = Flux.empty();
        Mono<String> mono = Mono.empty();


        flux.subscribe(System.out::println);
        mono.subscribe(System.out::println);
    }

    public static void filter(){
        Flux<Integer> numbers = Flux.range(1, 10)
                .filter(n -> n % 2 == 0);

        numbers.subscribe(System.out::println);
    }

    public static void concat(){
        Flux<String> flux1 = Flux.just("A", "B");
        Flux<String> flux2 = Flux.just("C", "D");

        Flux<String> result = Flux.concat(flux1, flux2);
        result.subscribe(System.out::println);
    }

    public static void conversions(){
        // From a list/collections
        List<String> names = List.of("Alice", "Bob");
        Flux<String> flux1 = Flux.fromIterable(names);
        flux1.subscribe(System.out::println);

        // From an array
        String[] arr = {"A", "B", "C"};
        Flux<String> flux2 = Flux.fromArray(arr);
        flux2.subscribe(System.out::println);

        // From a stream
        Stream<Integer> stream = Stream.of(1, 2, 3);
        Flux<Integer> flux3 = Flux.fromStream(stream);
        flux3.subscribe(System.out::println);
    }

    public static void transformations(){
        // Using map()
        Flux<Integer> flux1 = Flux.just(1, 2, 3).map(n -> n * 2);
        flux1.subscribe(System.out::println);



        // Using flatMap()
        Flux<String> flux2 = Flux.just("A", "B")
                .flatMap(letter -> Flux.just(letter.toLowerCase(), letter.toUpperCase()));

        flux2.subscribe(System.out::println);
    }

    public static void mergeFlux(){
        Flux<String> flux1 = Flux.just("1","2");
        Flux<String> flux2 = Flux.just("A", "B");

        Flux<String> merged = Flux.merge(flux1, flux2);
        merged.subscribe(System.out::println);
    }

    public static void zipFlux(){
        Flux<Integer> flux1 = Flux.just(2, 8);
        Flux<Integer> flux2 = Flux.just(1, 2);

        Flux<Integer> zipped = Flux.zip(flux1, flux2, (a, b) -> a + b);
        zipped.subscribe(System.out::println);
    }

    public static void optionalToMono(){
        Optional<String> optional = Optional.of("Hello");

        Mono<String> mono = Mono.justOrEmpty(optional);
        mono.subscribe(System.out::println);
    }

    public static void test6(){
        Flux<Integer> flux = Flux.just(1, 2, 0, 4);

        flux.map(n -> {
                if(n==0) return -1;
                else return 10 / n;
                })
                .subscribe(System.out::println);

        flux.map(n -> 10 / n)
                .onErrorContinue((throwable, item) -> {
                    System.out.println(-1);
                })
                .subscribe(System.out::println);

        flux.map(n -> 10 / n)
                .onErrorResume(e-> Flux.just(-1,4)).
                subscribe(System.out::println);

    }
}
