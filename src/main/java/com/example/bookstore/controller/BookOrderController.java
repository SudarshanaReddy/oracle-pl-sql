package com.example.bookstore.controller;

import com.example.bookstore.model.OrderRequest;
import com.example.bookstore.model.Orders;
import com.example.bookstore.model.SaveOrderResponse;
import com.example.bookstore.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/book")
public class BookOrderController {

    @Autowired
    private BookOrderService bookOrderService;

    @PostMapping(path = "/order", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SaveOrderResponse> saveOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(bookOrderService.orderBook(orderRequest));
    }
}
