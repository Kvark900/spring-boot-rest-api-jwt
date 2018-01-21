package com.kvark900.api.controller;

import com.kvark900.api.model.Book;
import com.kvark900.api.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping ("")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> allBooks = bookService.findAll();
        if(allBooks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(allBooks.isEmpty()){
            return new ResponseEntity<>(allBooks, HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping (value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        Book book = bookService.findById(id);
        if(book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping (value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book, BindingResult bindingResult,
                                         UriComponentsBuilder uriComponentsBuilder){
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (book == null)){
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        else{
            bookService.save(book);
            headers.setLocation(uriComponentsBuilder.path("/books/{id}").
                    buildAndExpand(book.getId()).toUri());
            return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable ("id") Long id, @RequestBody @Valid Book book,
                           BindingResult bindingResult){
        Book currentBook = bookService.findById(id);
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (book == null)){
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        if(currentBook == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.update(book);
        return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable ("id") Long id){
        Book bookToDelete = bookService.findById(id);
        if(bookToDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            bookService.delete(id);
            return new ResponseEntity<>(bookToDelete, HttpStatus.NO_CONTENT);
        }
    }
}
