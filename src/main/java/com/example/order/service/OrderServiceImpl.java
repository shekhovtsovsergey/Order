package com.example.order.service;

import com.example.order.integration.DeliveryServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final DeliveryServiceIntegration deliveryServiceIntegration;

    @Override
    public ResponseEntity<String> createOrder(){
        String storageFileName = deliveryServiceIntegration.getDelivery();
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


}
