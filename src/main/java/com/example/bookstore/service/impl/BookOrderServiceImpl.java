package com.example.bookstore.service.impl;


import com.example.bookstore.constants.Constants;
import com.example.bookstore.model.*;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UsersRepository;
import com.example.bookstore.service.BookOrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookOrderServiceImpl implements BookOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public SaveOrderResponse orderBook(final OrderRequest orderRequest) {
        final SaveOrderResponse saveOrderResponse = new SaveOrderResponse();
       try {
           Orders order = new Orders();
           order.setUsername(orderRequest.getUserName());
           order.setBookisbn(orderRequest.getBookIsbn());
           order.setQuantity(orderRequest.getQuantity());
           order = orderRepository.save(order);
           saveOrderResponse.setOrderId(order.getId());
           saveOrderResponse.setMessage("Order has placed order for user :" + orderRequest.getUserName() + " and"
                   + " for isbn: " + orderRequest.getBookIsbn());
       } catch (Exception exception) {
           saveOrderResponse.setOrderId(0);
           saveOrderResponse.setMessage("Failed to place order for user :" + orderRequest.getUserName() + " and"
            + "for isbn: " + orderRequest.getBookIsbn());
       }
       return saveOrderResponse;
    }
}
