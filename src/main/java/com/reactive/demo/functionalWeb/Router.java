package com.reactive.demo.functionalWeb;

import com.reactive.demo.functionalWeb.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/hello"), handler::hello);
    }
}