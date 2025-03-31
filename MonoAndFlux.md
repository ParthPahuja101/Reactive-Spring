## Mono and Flux

##### Mono and Flux are reactive publishers, used to handle asynchronous and  non-blocking operations.

##### Logging
```
Flux<String> flux1 = Flux.just("A", "B");
Flux<String> flux2 = Flux.just("C", "D");

Flux<String> result = Flux.concat(flux1, flux2);
result.subscribe(System.out::println);
```
<br />

##### Empty
```
Flux<String> flux = Flux.empty();
Mono<String> mono = Mono.empty();
```

<br />

##### Filter
```
Flux<Integer> numbers = Flux.range(1, 10)
    .filter(n -> n % 2 == 0);

numbers.subscribe(System.out::println);
```

<br />

##### Conversions
```
// From a list/collections
List<String> names = List.of("Alice", "Bob");
Flux<String> flux = Flux.fromIterable(names);

// From an array
String[] arr = {"A", "B", "C"};
Flux<String> flux = Flux.fromArray(arr);

// From a stream
Stream<Integer> stream = Stream.of(1, 2, 3);
Flux<Integer> flux = Flux.fromStream(stream);

```

<br />

##### Transformations
```
// Using map()
Flux<Integer> flux = Flux.just(1, 2, 3).map(n -> n * 2);
flux.subscribe(System.out::println);



// Using flatMap()
Flux<String> flux = Flux.just("A", "B")
    .flatMap(letter -> Flux.just(letter.toLowerCase(), letter.toUpperCase()));

flux.subscribe(System.out::println);

```
<br />

##### Combining Reactive Streams
```
// Using merge()
Flux<String> flux1 = Flux.just("1", "2");
Flux<String> flux2 = Flux.just("A", "B");

Flux<String> merged = Flux.merge(flux1, flux2);
merged.subscribe(System.out::println);



// Using zip()
Flux<Integer> flux1 = Flux.just(2, 8);
Flux<Integer> flux2 = Flux.just(1, 2);

Flux<Integer> zipped = Flux.zip(flux1, flux2, (a, b) -> a + b);
zipped.subscribe(System.out::println);

```

<br />

##### Optional to Mono
```
Optional<String> optional = Optional.of("Hello");

Mono<String> mono = Mono.justOrEmpty(optional);
mono.subscribe(System.out::println);

```