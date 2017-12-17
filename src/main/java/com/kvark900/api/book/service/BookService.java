package com.kvark900.api.book.service;

import com.kvark900.api.book.domain.Book;
import com.kvark900.api.book.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllCourses(String topicId){
        List<Book> books = new ArrayList<>();
        bookRepository.findByTopicId(topicId).
                forEach(books::add);
        return books;
    }

    public Book getCourseById(String id){
        return bookRepository.findOne(id);
    }

    public void addCourse(Book book){
        bookRepository.save(book);
    }

    public void deleteCourse(String id){
        bookRepository.delete(id);
    }

    public void updateCourse(Book book){
        bookRepository.save(book);
    }

}
