package com.reactive.demo.functionalWeb.Products;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductsHandler {
    private final ProductsService productsService;

    public ProductsHandler(ProductsService productService) {
        this.productsService = productService;
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productsService.getAllProducts(), Products.class);
    }

    public Mono<ServerResponse> getProductsById(ServerRequest request) {
        String id = request.pathVariable("id");
        return productsService.getProductById(id).flatMap(
                product -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(product)
        ).switchIfEmpty(ServerResponse.notFound().build());
    }

}
