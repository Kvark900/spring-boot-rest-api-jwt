package com.kvark900.api.author.service;

import com.kvark900.api.author.domain.Author;
import com.kvark900.api.author.domain.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Keno&Kemo on 17.12.2017..
 */
@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll (){
        return authorRepository.findAll();
    }

    public Author findById(Long id){
        return authorRepository.findOne(id);
    }

    public void save(Author author){
        authorRepository.save(author);
    }

    public void update(Author author){
        authorRepository.save(author);
    }

    public void delete(Long id){
        authorRepository.delete(id);
    }


}
