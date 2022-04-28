package com.example.bookstore.controller;

import com.example.bookstore.exception.RecordNotFoundException;
import com.example.bookstore.model.BookCatalogue;
import com.example.bookstore.model.OrdersResponse;
import com.example.bookstore.service.RetrieveBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/book")
public class RetrieveBookOrdersController {

    @Autowired
    private RetrieveBookOrderService retrieveBookOrderService;

    @GetMapping(path = "/catalogue", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<BookCatalogue>> getBookCatalogue() {
        final List<BookCatalogue> bookCatalogueList =  retrieveBookOrderService.getBookCatalogue();
        return ResponseEntity.ok(bookCatalogueList);
    }

    @GetMapping(path = "/allorders", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<OrdersResponse>> getAllOrders() {
        final List<OrdersResponse> ordersResponseList = retrieveBookOrderService.getAllOrders();
        return ResponseEntity.ok(ordersResponseList);
    }

    @GetMapping(path = "/order/{key}/{value}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<OrdersResponse>> getOrdersByUserName(@PathVariable("key") String key,
                                                                    @PathVariable("value") String value) {
        final List<OrdersResponse> ordersResponseList =  retrieveBookOrderService.getOrdersByKeyAndValue(key,value);
        if(ordersResponseList.size() > 0) {
            return ResponseEntity.ok(ordersResponseList);
        } else {
            throw new RecordNotFoundException("Order not found for given key:"+ key + " and" + " value:" + value);
        }
    }


}
