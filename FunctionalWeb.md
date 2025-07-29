## Functional Web Framework

##### The Functional Web Framework in Spring is an alternative to the traditional annotation-based Spring MVC/WebFlux approach.

##### It allows you to define your web application using a functional programming style, which can lead to more concise and readable code.

<br />

##### Handler Function
```
@Component
public class HelloHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello, Functional Web!");
    }
}
```
<br />

##### Router Function
```
@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/hello"), handler::hello);
    }
}
```

<br />

##### Combining Routes
```
// andRoute()

@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductsHandler handler) {
        return RouterFunctions
                .route(GET("/products"), handler::getAllProducts)
                .andRoute(GET("/products/{id}"), handler::getProductsById);
    }
}


// nest()

@Configuration
public class ProductsRouter {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductsHandler handler) {
        return nest(
                path("/products"),
                route(GET(""), handler::getAllProducts)
                        .andRoute(GET("/{id}"), handler::getProductsById)
        );
    }
}



// and()

@Configuration
public class ProductsRouter {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductsHandler handler) {
        RouterFunction<ServerResponse> getAllProducts = RouterFunctions.route(GET("/products"), handler::getAllProducts);
        RouterFunction<ServerResponse> getProductById = RouterFunctions.route(GET("/products/{id}"), handler::getProductsById);
        
        return getProductById.and(getAllProducts);
    }
}


// filter()

@Configuration
public class ProductsRouter {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductsHandler handler) {
        return RouterFunctions
                .route(GET("/products"), handler::getAllProducts)
                .andRoute(GET("/products/{id}"), handler::getProductsById)
                .filter((request, next) -> {
                    System.out.println("Before handler for request- "+ request.path());
                    return next.handle(request)
                            .doOnSuccess(response -> System.out.println("After handler with response- "+ response.statusCode()));
                });
    }
}
```

```

User user1 = new User(1,"Harry", "harry@griffindor.com");
User user2 = new User(2,"Ron", "ron@griffindor.com");
User user3 = new User(3,"Hermoine", "hermoine@griffindor.com");


/users - returns all users
/users/{id} - returns user by id 
```

