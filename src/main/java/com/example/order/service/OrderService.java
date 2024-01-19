package com.example.order.service;

import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<String> createOrder();

}
