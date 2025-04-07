package com.reactive.demo.bookManagement;

import com.reactive.demo.bookManagement.domain.AuditLogs;
import com.reactive.demo.bookManagement.domain.Book;
import com.reactive.demo.bookManagement.repo.AuditLogRepository;
import com.reactive.demo.bookManagement.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private  AuditLogRepository auditLogRepository;

//    @Transactional
//    public Mono<Book> saveBookWithAudit(Book book) {
//        Book bookToSave = new Book(null, book.getBookName(), book.getAuthor(), book.getPages(), book.getGenre(), book.getPublishedOn());
//        AuditLogs log = new AuditLogs(null, "Created Book: " + book.getBookName(), LocalDate.now());
//
//        return auditLogRepository.save(log).flatMap( logs -> bookRepository.save(bookToSave));
//    }

    @Transactional
    public Mono<Book> saveBookWithAudit(Book book) {
        Book bookToSave = new Book(null, book.getBookName(), book.getAuthor(), book.getPages(), book.getGenre(), book.getPublishedOn());
        AuditLogs log = new AuditLogs(null, "Created Book: " + book.getBookName(), LocalDate.now());

        return auditLogRepository.save(log).flatMap( logs -> {
            throw new RuntimeException("Simulated error");
        });
    }
}
