package com.kvark900.api.service;

import com.kvark900.api.model.Book;
import com.kvark900.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService() {
    }

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findOne(id);
    }

    public Book findByTitle (String title){return bookRepository.findByTitle(title);}

    public void save(Book book){
        bookRepository.save(book);
    }

    public void delete(Long id){
        bookRepository.delete(id);
    }

    public void update(Book book){
        bookRepository.save(book);
    }

}
