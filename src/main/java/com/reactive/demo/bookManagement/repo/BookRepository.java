package com.reactive.demo.bookManagement.repo;

import com.reactive.demo.bookManagement.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
    Flux<Book> findByAuthor(String author);
}

