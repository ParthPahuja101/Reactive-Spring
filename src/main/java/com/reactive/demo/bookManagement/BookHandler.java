package com.reactive.demo.bookManagement;

import com.reactive.demo.bookManagement.domain.Book;
import com.reactive.demo.bookManagement.exceptions.BookNotFoundException;
import com.reactive.demo.bookManagement.repo.AuditLogRepository;
import com.reactive.demo.bookManagement.repo.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;



@Component
public class BookHandler {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private BookService bookService;

    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.findAll(), Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return bookRepository.findById(id)
                .flatMap(book -> ServerResponse.ok().bodyValue(book))
                .switchIfEmpty(Mono.error(new BookNotFoundException("Book not found with id: " + id)));
    }

    public Mono<ServerResponse> getBookByIdFromHeader(ServerRequest request) {
        String headerId = request.headers().firstHeader("id");
        Long id = Long.parseLong(headerId);
        return bookRepository.findById(id)
                .flatMap(book -> ServerResponse.ok().bodyValue(book))
                .switchIfEmpty(Mono.error(new BookNotFoundException("Book not found with id: " + id)));
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(bookRepository::save)
                .flatMap(savedBook -> ServerResponse.ok().bodyValue(savedBook));
    }

    public Mono<ServerResponse> createBookWithAudit(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(bookService::saveBookWithAudit)
                .flatMap(savedBook -> ServerResponse.ok().bodyValue(savedBook));
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return request.bodyToMono(Book.class)
                .flatMap(newBook ->
                        bookRepository.findById(id)
                                .flatMap(existing -> {
                                    return bookRepository.save(newBook);
                                })
                )
                .flatMap(updated -> ServerResponse.ok().bodyValue(updated))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteBook(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return bookRepository.deleteById(id)
                .then(ServerResponse.noContent().build());
    }
}

