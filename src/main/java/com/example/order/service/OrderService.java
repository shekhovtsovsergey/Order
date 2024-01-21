package com.example.order.service;

import com.example.order.dto.PaymentDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<String> createOrder(PaymentDto paymentDto);
}
