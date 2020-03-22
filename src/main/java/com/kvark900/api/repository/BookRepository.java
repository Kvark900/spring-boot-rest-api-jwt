package com.kvark900.api.repository;


import com.kvark900.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAllIgnoreCase(String title);

    List<Book> findByTopicsId(Long id);

    List<Book> findByAuthorsId(Long id);

    @Query("SELECT b " +
            "FROM Book b " +
            "JOIN FETCH b.authors " +
            "jOIN FETCH b.topics")
    List<Book> findAll();

}
