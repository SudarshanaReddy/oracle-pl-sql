package com.example.bookstore.service.impl;

import com.example.bookstore.constants.Constants;
import com.example.bookstore.model.BookCatalogue;
import com.example.bookstore.model.Orders;
import com.example.bookstore.model.OrdersResponse;
import com.example.bookstore.model.Users;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UsersRepository;
import com.example.bookstore.service.RetrieveBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class RetrieveBookOrderServiceImpl implements RetrieveBookOrderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public BookCatalogue getAllBooksViaStoredProc() {

        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("GET_ALL_BOOKS");

        storedProcedureQuery.execute();

        return null;
    }

    @Override
    public List<BookCatalogue> getBookCatalogue() {
        return  bookRepository.findAll();
    }

    @Override
    public List<OrdersResponse> getAllOrders() {
        final List<OrdersResponse> ordersResponseList = new ArrayList<>();
        final List<Orders> orders = orderRepository.findAll();
        final List<Users> users = usersRepository.findAll();
        final List<BookCatalogue> bookCatalogues = bookRepository.findAll();
        for(Orders bookOrder : orders) {
            final OrdersResponse ordersResponse = new OrdersResponse();
            for(Users user : users) {
                if(user.getUsername().equalsIgnoreCase(bookOrder.getUsername())) {
                    ordersResponse.setUserName(user.getUsername());
                    ordersResponse.setFirstName(user.getFirstname());
                    ordersResponse.setLastName(user.getLastname());
                }
            }

            for(BookCatalogue bookCatalogue : bookCatalogues) {
                if(bookCatalogue.getIsbn().equalsIgnoreCase(bookOrder.getBookisbn())) {
                    ordersResponse.setIsbn(bookCatalogue.getIsbn());
                    ordersResponse.setBookAuthor(bookCatalogue.getAuthor());
                    ordersResponse.setBookName(bookCatalogue.getName());
                    ordersResponse.setPublicationYear(bookCatalogue.getYear());
                    ordersResponse.setBookQuantity(bookOrder.getQuantity());
                }
            }
            ordersResponseList.add(ordersResponse);
        }
        return ordersResponseList;
    }

    @Override
    public List<OrdersResponse> getOrdersByKeyAndValue(String key, String value) {
        if(key.equalsIgnoreCase(Constants.USER_NAME)) {
            return findOrders(Constants.USER_NAME, value);
        } else if(key.equalsIgnoreCase(Constants.ISBN)) {
            return findOrders(Constants.ISBN, value);
        }
        return new ArrayList<>();
    }

    private List<OrdersResponse> findOrders(final String key, final String value) {
        final List<OrdersResponse> ordersResponseList = new ArrayList<>();
        List<Orders> order = null;
        if(key.equalsIgnoreCase(Constants.USER_NAME)) {
            order = orderRepository.findByUsername(value);
        } else if(key.equalsIgnoreCase(Constants.ISBN)) {
            order = orderRepository.findByIsbn(value);
        }

        final List<Users> users = usersRepository.findAll();
        final List<BookCatalogue> bookCatalogues = bookRepository.findAll();

        for(Orders bookOrder : order) {
            final OrdersResponse ordersResponse = new OrdersResponse();
            for(Users user : users) {
                if(user.getUsername().equalsIgnoreCase(bookOrder.getUsername())) {
                    ordersResponse.setUserName(user.getUsername());
                    ordersResponse.setFirstName(user.getFirstname());
                    ordersResponse.setLastName(user.getLastname());
                }
            }

            for(BookCatalogue bookCatalogue : bookCatalogues) {
                if(bookCatalogue.getIsbn().equalsIgnoreCase(bookOrder.getBookisbn())) {
                    ordersResponse.setIsbn(bookCatalogue.getIsbn());
                    ordersResponse.setBookAuthor(bookCatalogue.getAuthor());
                    ordersResponse.setBookName(bookCatalogue.getName());
                    ordersResponse.setPublicationYear(bookCatalogue.getYear());
                    ordersResponse.setBookQuantity(bookOrder.getQuantity());
                }
            }
            ordersResponseList.add(ordersResponse);
        }

        return ordersResponseList;
    }
}
