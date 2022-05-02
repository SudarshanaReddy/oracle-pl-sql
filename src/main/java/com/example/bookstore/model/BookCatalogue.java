package com.example.bookstore.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedStoredProcedureQuery(name = "findAllBooks",
        procedureName = "GET_ALL_BOOKS")
@Table(name ="bookcatalogue")
public class BookCatalogue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "name")
    private String name;
    @Column(name="author")
    private String author;
    @Column(name = "year")
    private String year;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private String quantity;

}
