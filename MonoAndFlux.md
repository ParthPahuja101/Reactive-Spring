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


<br />

##### Questions
```

1)
Input-
Flux.range(1, 5)
Output-
filter out odd numbers and multiply the remaining numbers by 2.


2)
Input-
Flux.just("A", "B")
Flux.just("C", "D")
Output-
a
b
c
d



3)
Input -
Flux.just("apple", "banana", "cherry")
Flux.just(1, 2, 3)

Output-
APPLE  
BANANA  
CHERRY  
10  
20  
30


4)
Input -
Flux.just("1,2")
Flux.just("3,4")

Concatenate the streams, split each string into individual numbers, and print them.
Output-
1
2
3
4

5)
Input -
Flux.just(1, 2, 3)
Flux.just(4, 5, 6)

Zip the streams and calculate the sum of corresponding elements.
Output-
5
7
9


6)
Input-
Flux.just(1, 2, 0, 4)
 
Divide 10 by each number, handle any division-by-zero errors, and fallback to a default value of -1 for errors.
Output-
10  
5  
-1  
2
```