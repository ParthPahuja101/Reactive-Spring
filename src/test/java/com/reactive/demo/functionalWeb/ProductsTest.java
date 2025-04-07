package com.reactive.demo.functionalWeb;


import com.reactive.demo.functionalWeb.Products.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductsTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetAllProducts() {
        webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Products.class)
                .hasSize(5);
    }

}
