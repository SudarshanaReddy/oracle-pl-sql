package com.example.bookstore.repository;


import com.example.bookstore.model.BookCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface BookRepository extends JpaRepository<BookCatalogue, String> {

}
