package com.example.bookstore.repository;

import com.example.bookstore.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
    @Query(value = "SELECT * FROM Orders WHERE username = ?1", nativeQuery = true)
    List<Orders> findByUsername(String userName);

    @Query(value = "SELECT * FROM Orders WHERE bookisbn = ?1", nativeQuery = true)
    List<Orders> findByIsbn(String isbn);
}
