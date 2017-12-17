package com.kvark900.api.book.domain;
import com.kvark900.api.author.domain.Author;
import com.kvark900.api.topic.domain.Topic;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    private Long id;
    private String title;

    @ManyToMany
    private Author author;

    private String yearOfPublication;
    private String description;

    @ManyToMany
    private List<Topic> topic;

    //Constructors
    public Book(Long id, String title, Author author, String yearOfPublication, String description, List<Topic> topic) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.description = description;
        this.topic = topic;
    }

    public Book() {
    }

    //getters and setters
    public List<Topic> getTopic() {
        return topic;
    }
    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
