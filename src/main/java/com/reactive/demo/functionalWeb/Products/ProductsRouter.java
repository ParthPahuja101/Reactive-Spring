package com.reactive.demo.functionalWeb.Products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ProductsRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductsHandler handler) {
        return RouterFunctions
                .route(GET("/products"), handler::getAllProducts);
    }

    @Bean
    public RouterFunction<ServerResponse> productByIdRoutes(ProductsHandler handler) {
        return RouterFunctions
                .route(GET("/products/{id}"), handler::getProductsById);
    }

}