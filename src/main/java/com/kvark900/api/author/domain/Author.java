package com.kvark900.api.author.domain;

import com.kvark900.api.book.domain.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by Keno&Kemo on 17.12.2017..
 */
@Entity
public class Author {
    @Id
    private Long id;
    private String name;
    private String surname;

    @ManyToMany
    private List<Book> books;

    public Author(Long id, String name, String surname, List<Book> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
