package com.kvark900.api.service;

import com.kvark900.api.model.Book;
import com.kvark900.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private BookRepository bookRepository;

    public BookService() {
    }

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitleAllIgnoreCase(title);
    }

    public List<Book> findByTopicsId(Long id) {
        return bookRepository.findByTopicsId(id);
    }

    public List<Book> findByAuthorsId(Long id) {
        return bookRepository.findByAuthorsId(id);
    }


    public void save(Book book) {
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(Book book) {
        bookRepository.save(book);
    }

}
