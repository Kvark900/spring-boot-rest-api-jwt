package com.kvark900.api.book.web;

import com.kvark900.api.book.domain.Book;
import com.kvark900.api.book.service.BookService;
import com.kvark900.api.topic.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping ("/topics/{topicId}/courses")
    public List<Book> getAllCourses(@PathVariable String topicId){

        return bookService.getAllCourses(topicId);
    }

    @RequestMapping ("/topics/{topicId}/courses/{id}")
    public Book getCourse(@PathVariable String id){
        return bookService.getCourseById(id);
    }

    @RequestMapping (method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public void addCourse(@PathVariable String topicId, @RequestBody Book book){
        book.setTopic(new Topic(topicId, "","", books));
        bookService.addCourse(book);
    }

    @RequestMapping (method = RequestMethod.DELETE,
            value = "/topics/{topicId}/courses/{id}")
    public void deleteCourse(@PathVariable String id){
        bookService.deleteCourse(id);
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
    public void updateCourse(@PathVariable String id, @RequestBody Book book){
        bookService.updateCourse(book);
    }

}
