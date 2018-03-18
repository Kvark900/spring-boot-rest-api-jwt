package com.kvark900.api.controller;

import com.kvark900.api.model.Author;
import com.kvark900.api.service.AuthorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Keno&Kemo on 17.12.2017..
 */
@RestController
@RequestMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> allAuthors = authorService.findAll();
        if(allAuthors == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(allAuthors.isEmpty()){
            return new ResponseEntity<>(allAuthors, HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id){
        Author author = authorService.findById(id);
        if(author == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Author> saveAuthor(@RequestBody @Valid Author author, BindingResult bindingResult,
                                         UriComponentsBuilder uriComponentsBuilder){
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (author == null)){
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        else{
            authorService.save(author);
            headers.setLocation(uriComponentsBuilder.path("/authors/{id}").
                    buildAndExpand(author.getId()).toUri());
            return new ResponseEntity<>(author, headers, HttpStatus.CREATED);
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable ("id") Long id, @RequestBody @Valid Author author,
                                               BindingResult bindingResult){
        Author currentAuthor = authorService.findById(id);
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (author == null)){
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        if(currentAuthor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.update(author);
        return new ResponseEntity<>(author, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable ("id") Long id){
        Author authorToDelete = authorService.findById(id);
        if(authorToDelete == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            authorService.delete(id);
            return new ResponseEntity<>(authorToDelete, HttpStatus.NO_CONTENT);
        }
    }

}
