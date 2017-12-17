package com.kvark900.api.topic.domain;


import com.kvark900.api.book.domain.Book;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {
    @Id
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    private List<Book> books;


    public Topic(Long id, String name, String description, List<Book> books) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.books = books;
    }

    public Topic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
