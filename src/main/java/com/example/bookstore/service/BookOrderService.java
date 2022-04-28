package com.example.bookstore.service;

import com.example.bookstore.model.BookCatalogue;
import com.example.bookstore.model.OrderRequest;
import com.example.bookstore.model.Orders;
import com.example.bookstore.model.SaveOrderResponse;

import java.util.List;

public interface BookOrderService {
    SaveOrderResponse orderBook(final OrderRequest orderRequest);
}
