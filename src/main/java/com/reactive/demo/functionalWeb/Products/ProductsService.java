package com.reactive.demo.functionalWeb.Products;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductsService {


    public Flux<Products> getAllProducts() {
        return Flux.just(
                new Products("1", "Product 1", 10.0),
                new Products("2", "Product 2", 9.9),
                new Products("3", "Product 3", 16.0),
                new Products("4", "Product 4", 1.5),
                new Products("5", "Product 5", 25.9));
    }

    public Mono<Products> getProductById(String id) {
        return getAllProducts().filter(product -> product.id().equals(id))
                .next();
    }
}
