package com.example.bookstore.model;

import lombok.Data;

import java.util.List;

@Data
public class OrdersResponse {

    private String userName;
    private String firstName;
    private String lastName;
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private String publicationYear;

}
