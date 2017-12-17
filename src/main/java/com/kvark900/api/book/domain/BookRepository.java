package com.kvark900.api.book.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTopicId(String topicId);

}
