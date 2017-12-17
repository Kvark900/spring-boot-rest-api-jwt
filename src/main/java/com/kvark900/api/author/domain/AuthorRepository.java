package com.kvark900.api.author.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Keno&Kemo on 17.12.2017..
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
