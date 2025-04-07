package com.reactive.demo.bookManagement;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<?> bookRoutes(BookHandler handler) {
        return route(GET("/books"), handler::getAllBooks)
                .andRoute(GET("/books/{id}"), handler::getBookById)
                .andRoute(POST("/books"), handler::createBook)
                .andRoute(PUT("/books/{id}"), handler::updateBook)
                .andRoute(DELETE("/books/{id}"), handler::deleteBook)
                .andRoute(GET("/booksWithHeader"), handler::getBookByIdFromHeader)
                .andRoute(POST(("/booksWithAudit")), handler::createBookWithAudit);
    }
}
