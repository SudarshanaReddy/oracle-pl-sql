package com.example.bookstore.controller;

import com.example.bookstore.model.BookCatalogue;
import com.example.bookstore.model.SaveOrderResponse;
import com.example.bookstore.service.RetrieveBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/v1/storedproc/book")
public class StoredProcedureBookCatalogue {

    @Autowired
    private RetrieveBookOrderService retrieveBookOrderService;

    @PostMapping(path = "/catalogue", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookCatalogue> getBookCatalogue() throws SQLException {
        return ResponseEntity.ok(retrieveBookOrderService.getAllBooksViaStoredProc());
    }
}
