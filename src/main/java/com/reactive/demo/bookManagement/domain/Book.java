package com.reactive.demo.bookManagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


@Table("books")
public class Book {
    @Id
    private Long id;
    private String bookName;
    private String author;
    private int pages;
    private Genre genre;
    private LocalDate publishedOn;

    // No-argument constructor
    public Book() {}

    // All-argument constructor
    public Book(Long id, String bookName, String author, int pages, Genre genre, LocalDate publishedOn) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
        this.genre = genre;
        this.publishedOn = publishedOn;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }
}

enum Genre {
    FICTION, NON_FICTION, SCIENCE, FANTASY
}