
## Spring Webclient

<br />


#### Webclient that follows non-blocking and reactive protocol, used in reactive programming to integrate with other microservices or make api calls from our main service.

<br />

##### Creating Webclient-
```
// Basic client
WebClient webClient = WebClient.create();

// With base URL
WebClient webClient = WebClient.create("https://api.example.com");

// With customization
WebClient webClient = WebClient.builder()
    .baseUrl("https://api.example.com")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .defaultCookie("session", "123")
    .build();

```
<br />

##### Adding custom connection configuration Webclient-
```
HttpClient httpClient = HttpClient.create()
    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
    .responseTimeout(Duration.ofMillis(5000));

WebClient webClient = WebClient.builder()
    .clientConnector(new ReactorClientHttpConnector(httpClient))
    .build();

```
<br />

##### HTTP Method Call
```
// GET
Mono<User> user = webClient.get()
    .uri("/users)
    .header("Authorization", "Bearer " + token)
    .retrieve()
    .bodyToMono(User.class);
    
// GET with path varialble
Mono<User> user = webClient.get()
    .uri("/users/{id}", userId)
    .header("Authorization", "Bearer " + token)
    .retrieve()
    .bodyToMono(User.class);
    
    
// GET with query parameter
Mono<User> user = webClient.get()
    .uri("/users/{id}", userId)
    .queryParam("q", query)
    .queryParam("category", category)
    .header("Authorization", "Bearer " + token)
    .retrieve()
    .bodyToMono(User.class);


// POST
Mono<User> createdUser = webClient.post()
    .uri("/users")
    .contentType(MediaType.APPLICATION_JSON)
    .body(Mono.just(newUser), User.class)
    .retrieve()
    .bodyToMono(User.class);


// PUT
Mono<User> updatedUser = webClient.put()
    .uri("/users/{id}", userId)
    .contentType(MediaType.APPLICATION_JSON)
    .bodyValue(userToUpdate)
    .retrieve()
    .bodyToMono(User.class);


// DELETE
Mono<Void> deletion = webClient.delete()
    .uri("/users/{id}", userId)
    .retrieve()
    .bodyToMono(Void.class);


// PATCH
Mono<User> patchedUser = webClient.patch()
    .uri("/users/{id}", userId)
    .contentType(MediaType.APPLICATION_JSON)
    .bodyValue(patchData)
    .retrieve()
    .bodyToMono(User.class);

```

<br />
<br />

##### exchangeToMono() / exchange() 
```
Mono<User> user = webClient.get()
    .uri("/users/{id}", userId)
    .exchangeToMono(clientResponse -> {
        if (clientResponse.statusCode().is2xxSuccessful()) {
            return clientResponse.bodyToMono(String.class);  // Extract response body
        } else {
            return Mono.error(new RuntimeException("Error: " + clientResponse.statusCode()));
        }
    });
```
<br />
<br />
<br />
<br />
<br />
<br />

##### onErrorReturn()
```
Mono<String> data = WebClient.create("http://example.com")
    .get()
    .uri("/data")
    .retrieve()
    .bodyToMono(String.class)
    .onErrorReturn("Fallback response");;
```


##### onErrorResume()
```
Mono<String> data = WebClient.create("http://example.com")
    .get()
    .uri("/data")
    .retrieve()
    .bodyToMono(String.class)
    .onErrorResume(error -> {
        System.out.println("Error occurred: " + error.getMessage());
        return Mono.just("Alternative data source used");
    });
```



##### onStatus()
```
Mono<User> user =  webClient.get()
    .uri("/users/{id}", userId)
    .retrieve()
    .onStatus(HttpStatus::is4xxClientError,
              response -> Mono.error(new RuntimeException("Client Error")))
    .onStatus(HttpStatus::is5xxServerError,
              response -> Mono.error(new RuntimeException("Server Error")))
    .bodyToMono(String.class)
    .doOnError(error -> System.out.println("Error: " + error.getMessage()));

```

##### doOnError()
```
Mono<User> user =  webClient.get()
    .uri("/users/{id}", userId)
    .retrieve()
    .bodyToMono(String.class)
    .doOnError(error -> System.out.println("Error: " + error.getMessage()));

```



