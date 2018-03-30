package com.kvark900.api.repository;


import com.kvark900.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAllIgnoreCase(String title);

}
