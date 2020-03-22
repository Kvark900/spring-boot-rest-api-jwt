package com.kvark900.api.service;

import com.kvark900.api.model.Author;
import com.kvark900.api.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 17.12.2017..
 */
@Service
@Transactional
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService() {
    }

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> findAll (){
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id){
        return authorRepository.findById(id);
    }

    public Author findByNameAndSurname(String name, String surname){
        return authorRepository.findByNameAndSurnameAllIgnoreCase(name, surname);
    }

    public void save(Author author){
        authorRepository.save(author);
    }

    public void update(Author author){
        authorRepository.save(author);
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }


}
